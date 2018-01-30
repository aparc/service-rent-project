package ru.aparc.facadeDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PostFacadeDao {

    static {
        final EntityManagerFactory em = Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }


}
