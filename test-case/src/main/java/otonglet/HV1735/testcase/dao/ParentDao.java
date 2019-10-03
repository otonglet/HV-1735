package otonglet.HV1735.testcase.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import otonglet.HV1735.testcase.entity.Parent;

@Repository
public class ParentDao {
    @Autowired
    private EntityManager entityManager;

    public Parent get(long id) {
        return entityManager.find(Parent.class, id);
    }
}
