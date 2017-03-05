package com.alodiga.persistence.dao;

 
import com.alodiga.persistence.domain.City;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CityDAO extends BaseDAO<City, Long> {
 

    public List<String> listCitiesFromState(String state){
        try{ 
            List<String> list = getCriteria()
                    .add(Restrictions.like("stateCode", state))
                    .addOrder(Order.desc("priority"))
                    .addOrder(Order.asc("name"))
                    .setProjection(Projections.property("name"))
                    .list();  
            return list;
        }catch(Exception e){
              System.out.println("Exception:: stateCode = " + state);
            return null;
        } 
    }
    
}
