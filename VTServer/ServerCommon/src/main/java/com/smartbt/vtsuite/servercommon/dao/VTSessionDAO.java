/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.VTSession;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.Validate;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eduardo, Ariel Saavedra
 */
public class VTSessionDAO extends BaseDAO<VTSession> {

    protected static VTSessionDAO dao;

    public VTSessionDAO() {
        //super(VTHibernateUtil.getSession().class);
    }

    public static VTSessionDAO get() {
        if (dao == null) {
            dao = new VTSessionDAO();
        }
        return dao;
    }

    public VTSession saveOrUpdateSession(Terminal terminal, Clerk clerk) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class)
                .createAlias("clerk", "clerk")
                .add(Restrictions.eq("clerk.id", clerk.getId()));
        VTSession vtSession = (VTSession) criteria.uniqueResult();

        if (vtSession == null) {
            vtSession = new VTSession();
        }

        // Logic to create a new token and session
        String token = Utils.generateToken();
        vtSession.setToken(token);
        vtSession.setTerminal(terminal);
        vtSession.setClerk(clerk);
        vtSession.setLastUpdated(new Date());
        HibernateUtil.getSession().saveOrUpdate(vtSession);
        return vtSession;
    }

    public VTSession getSessionByToken(String token) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class).add(Restrictions.eq("token", token));
        return (VTSession) criteria.uniqueResult();
    }

    public VTSession getSessionByClerk(Clerk clerk) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class)
                .createAlias("clerk", "clerk")
                .add(Restrictions.eq("clerk.id", clerk.getId()));
        return (VTSession) criteria.uniqueResult();
    }

    /**
     * Verifies if token is valid.
     *
     * @param token The token
     * @return session object
     */
    public int validateSession(String token) {
        int result;
        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class).add(Restrictions.eq("token", token));
        VTSession clerkSession = (VTSession) criteria.uniqueResult();
        if (clerkSession != null) {

            int aliveSessionTime;

            if (clerkSession.getClerk().getCustomer() != null) {
                aliveSessionTime = ((Merchant) clerkSession.getClerk().getCustomer().getMerchant().toArray()[0]).getAliveSessionTime();
            } else if (clerkSession.getClerk().getMerchant() != null) {
                aliveSessionTime = clerkSession.getClerk().getMerchant().getAliveSessionTime();
            } else {
                aliveSessionTime = clerkSession.getTerminal().getMerchant().getAliveSessionTime();
            }

            if (!Validate.isTime(clerkSession.getLastUpdated().getTime(), aliveSessionTime * 1000)) {
                clerkSession.setLastUpdated(new Date());
                clerkSession.setSessionInfo(String.valueOf(Constants.CODE_SESSION_VALID));
                result = Constants.CODE_SESSION_VALID;
                HibernateUtil.getSession().saveOrUpdate(clerkSession);
            } else {
                clerkSession.setSessionInfo(String.valueOf(Constants.CODE_SESSION_EXPIRE));
                result = Constants.CODE_SESSION_EXPIRE;
            }

        } else {
            result = Constants.CODE_SESSION_LOST;
        }
        return result;
    }

    public EntityType clientHierarchy(String token) {
        VTSession clerkSession;
        EntityType entityType = null;

        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class).add(Restrictions.eq("token", token));
        clerkSession = (VTSession) criteria.uniqueResult();

        if (clerkSession != null) {
            if (clerkSession.getClerk().getCustomer() != null) {
                entityType = EntityType.CUSTOMER;
            } else if (clerkSession.getClerk().getMerchant() != null) {
                entityType = EntityType.MERCHANT;
            } else if (clerkSession.getClerk().getTerminal() != null) {
                entityType = EntityType.TERMINAL;
            }
        }
        return entityType;
    }

    //These methods are just for support SOAP
    public int clerkIdByToken(String token) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Clerk.class).createAlias("vTSession", "vTSession").add(Restrictions.eq("vTSession.token", token));
        Clerk clerk = (Clerk) criteria.uniqueResult();
        return clerk.getId();
    }

    public int terminalIdByToken(String token) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Terminal.class).createAlias("vTSession", "vTSession").add(Restrictions.eq("vTSession.token", token));
        Terminal terminal = (Terminal) criteria.uniqueResult();
        return terminal.getId();
    }

    public int merchantIdByToken(String token) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Merchant.class).createAlias("terminal", "terminal").createAlias("terminal.vTSession", "vTSession").add(Restrictions.eq("vTSession.token", token));
        Merchant merchant = (Merchant) criteria.uniqueResult();
        return merchant.getId();
    }
}
