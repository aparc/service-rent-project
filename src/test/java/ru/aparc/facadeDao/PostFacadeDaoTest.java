package ru.aparc.facadeDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.aparc.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class PostFacadeDaoTest {

    private EntityManager em;
    private PostFacadeDao dao;

    @Before
    public void setUp() throws Exception {
        em = Persistence.createEntityManagerFactory("TestPersistenceUnit").createEntityManager();
        dao = new PostFacadeDao(em);
    }

    @After
    public void tearDown() throws Exception {
        em.close();
    }

    @Test
    public void createUser() {
        em.getTransaction().begin();
        User user = new User();
        user.setLogin("root");
        user.setPassword("qwerty");

        em.persist(user);
        em.getTransaction().commit();

        assertEquals("root", em.find(User.class, user.getUserId()).getLogin());

    }

    @Test
    public void findUserById() {
        em.getTransaction().begin();
        User user = new User();
        user.setLogin("root");
        user.setPassword("qwerty");

        User userFind = dao.findUserById(1);

        assertEquals("root", user.getLogin());
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    public void postInTransaction() {
    }
}