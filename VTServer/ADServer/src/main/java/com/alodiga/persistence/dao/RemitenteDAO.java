package com.alodiga.persistence.dao;

 
import com.alodiga.persistence.domain.Remitente;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RemitenteDAO extends BaseDAO<Remitente, Long> {

    public Remitente getRemitenteByPhone(String telefono) {
     return (Remitente)getCriteria()
                .add(Restrictions.eq("telefono", telefono))
                .setMaxResults(1).uniqueResult();
    }

    
    
}
