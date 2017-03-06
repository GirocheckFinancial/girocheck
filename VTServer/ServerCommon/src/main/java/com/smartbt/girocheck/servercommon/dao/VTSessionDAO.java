/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.User;
import com.smartbt.girocheck.servercommon.model.VTSession;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.Validate;
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

    public VTSession saveOrUpdateSession(User user) {

        VTSession vtSession = new VTSession();
        if (user != null) {
            Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class)
                    .createAlias("user", "user")
                    .add(Restrictions.eq("user.id", user.getId()));
            vtSession = (VTSession) criteria.uniqueResult();

            if (vtSession == null) {
                vtSession = new VTSession();
            }

            // Logic to create a new token and session
            String token = Utils.generateToken();
            vtSession.setToken(token);
            vtSession.setUser(user);
            vtSession.setLastUpdate(new Date());
            HibernateUtil.getSession().saveOrUpdate(vtSession);

            return vtSession;
        }
        return vtSession;
    }
 

    public VTSession getSessionByToken(String token) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class).add(Restrictions.eq("token", token));
        return (VTSession) criteria.uniqueResult();
    }

    public VTSession getSessionByUser(User user) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class)
                .createAlias("user", "user")
                .add(Restrictions.eq("user.id", user.getId()));
        return (VTSession) criteria.uniqueResult();
    }

    /**
     * Verifies if token is valid. Return If the session expire or not
     *
     * @param token The token
     * @return session object
     */
    public int validateSession(String token) {
        int result;
        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class).add(Restrictions.eq("token", token));
        VTSession userSession = (VTSession) criteria.uniqueResult();
        if (userSession != null) {
            int aliveSessionTime = 36000000;
            long auxtime = System.currentTimeMillis() - userSession.getLastUpdate().getTime();

            if (!Validate.isTime(userSession.getLastUpdate().getTime(), aliveSessionTime)) {
                userSession.setLastUpdate(new Date());
                userSession.setSessionInfo(String.valueOf(Constants.CODE_SESSION_VALID));
                result = Constants.CODE_SESSION_VALID;
                HibernateUtil.getSession().saveOrUpdate(userSession);
            } else {
                userSession.setSessionInfo(String.valueOf(Constants.CODE_SESSION_EXPIRE));
                result = Constants.CODE_SESSION_EXPIRE;
            }

        } else {
            result = Constants.CODE_SESSION_LOST;
        }
        return result;
    }

    //These methods are just for support SOAP
    public int userIdByToken(String token) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(User.class).createAlias("vTSession", "vTSession").add(Restrictions.eq("vTSession.token", token));
        User user = (User) criteria.uniqueResult();
        return user.getId();
    }

    public void deleteSession(String token) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(VTSession.class).add(Restrictions.eq("token", token));
        VTSession vtsession = (VTSession) criteria.uniqueResult();

        super.delete(vtsession);

    }

    public void deleteSessionByUser(int idUser) {
        String sql = "delete from vtsession WHERE id_user = " + idUser;

        int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();

        System.out.println("Deleted " + updatedRows + " sessions..");
    }

}
