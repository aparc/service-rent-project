package ru.aparc.facadeDao;

import ru.aparc.domain.User;
import ru.aparc.domain.UserInfo;

import javax.persistence.EntityManager;

public class UserFacadeDao {

    private final EntityManager em;

    public UserFacadeDao(EntityManager em) {
        this.em = em;
    }

    public User createUser(User user){
        em.persist(user.getInfo());
        em.persist(user);
//        em.getTransaction().begin();
        UserInfo info = user.getInfo();
        em.refresh(info);
//        em.getTransaction().commit();
        return user;
    }

    public User findUserByLogin(String login) {
        em.getTransaction().begin();
        User user = (User) em.createQuery("from User where login =:login")
                .setParameter("login", login).getSingleResult();
        em.getTransaction().commit();
        return user;
    }
}
