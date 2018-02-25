//package ru.aparc.facadeDao;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import ru.aparc.domain.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//
//import static org.junit.Assert.*;
//
//public class PostFacadeDaoTest {
//
//    private EntityManager em;
//    private PostFacadeDao dao;
//
//    @Before
//    public void setUp() throws Exception {
//        em = Persistence.createEntityManagerFactory("TestPersistenceUnit").createEntityManager();
//        dao = new PostFacadeDao(em);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        em.close();
//    }
//
//    @Test
//    public void testCreateUser() {
//        em.getTransaction().begin();
//        User user = new User();
//        user.setLogin("root");
//        user.setPassword("qwerty");
//
//        em.persist(user);
//        em.getTransaction().commit();
//
//        assertEquals("root", em.find(User.class, user.getUserId()).getLogin());
//
//    }
//
//    @Test
//    public void testFindUserById() {
//        User user = new User();
//        user.setLogin("admin");
//        user.setPassword("qwerty");
//
//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
//
//        User userFind = dao.findUserByLogin("admin");
//
//
//        assertEquals("admin", userFind.getLogin());
//        assertEquals("qwerty", userFind.getPassword());
//    }
//
//    @Test
//    public void testPostInTransaction() {
//        User obj = new User();
//        obj.setLogin("test");
//        obj.setPassword("qwerty");
//        em.getTransaction().begin();
//        em.persist(obj);
//        em.getTransaction().commit();
//
//        dao.postInTransaction(obj.getLogin(), "some test text");
//        User obj2 = dao.findUserByLogin("test");
//
//        assertEquals("test", obj2.getLogin());
//        assertEquals("qwerty", obj2.getPassword());
//        assertTrue(obj2.getPostList().size() == 1);
//    }
//}