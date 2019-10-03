package otonglet.HV1735.testcase.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import otonglet.HV1735.testcase.entity.LazyChild;

@Repository
public class LazyChildDao {
    @Autowired
    private EntityManager entityManager;

    public LazyChild getLazyChildWithLazilyInitializedParent(long id) {
        return entityManager.find(LazyChild.class, id);
    }
}
