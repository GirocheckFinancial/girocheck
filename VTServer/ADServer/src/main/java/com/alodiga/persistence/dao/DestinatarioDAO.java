package com.alodiga.persistence.dao;

 
import com.alodiga.persistence.domain.Destinatario;
import com.alodiga.persistence.domain.Remitente;
import java.util.List;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DestinatarioDAO extends BaseDAO<Destinatario, Long> {

    public List<Destinatario> getDestinatariosByRemitente(Long id) {
         ProjectionList projectionList = Projections.projectionList()
                    .add(Projections.property("id").as("id"))
                    .add(Projections.property("nombre").as("nombre"))
                    .add(Projections.property("direccion").as("direccion"))
                    .add(Projections.property("municipio").as("municipio"))
                    .add(Projections.property("departamento").as("departamento"))
                    .add(Projections.property("pais").as("pais"))
                    .add(Projections.property("telefono").as("telefono"));
         
        return getCriteria().createAlias("remitente", "remitente")
                .add(Restrictions.eq("remitente.id", id))
                .setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(Destinatario.class))
                .list();
    }

    
    
}
