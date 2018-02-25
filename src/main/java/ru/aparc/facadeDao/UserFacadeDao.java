package ru.aparc.facadeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aparc.domain.User;
import ru.aparc.domain.UserInfo;

import javax.persistence.EntityManager;

@Service
public class UserFacadeDao {

    private final EntityManager em;

    @Autowired
    public UserFacadeDao(EntityManager em) {
        this.em = em;
    }

    public User createUser(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public User updateUser(User user) {
        return em.merge(user);
    }

    public User findUserByLogin(String login) {
        em.getTransaction().begin();
        User user = (User) em.createQuery("from User where login =:login")
                .setParameter("login", login).getSingleResult();
        em.getTransaction().commit();
        return user;
    }

    public void removeUser(User user) {
        UserInfo info = findUserByLogin(user.getLogin()).getInfo();
        em.getTransaction().begin();
        em.remove(info);
        em.remove(user);
        em.getTransaction().commit();
    }
}
