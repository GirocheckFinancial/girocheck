package com.alodiga.persistence.dao;

 
import com.alodiga.persistence.domain.State; 
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StateDAO extends BaseDAO<State, Long> {

      public String getStateCodeByCode(String code){
        return (String)getCriteria()
                    .add(Restrictions.like("code", code))
                    .setProjection(Projections.property("stateCode"))
                    .setMaxResults(1)
                    .uniqueResult(); 
    }
    
}
