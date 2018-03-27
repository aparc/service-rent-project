package ru.aparc.facadeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aparc.domain.User;
import ru.aparc.domain.UserInfo;

import javax.persistence.EntityManager;

@Service
@Transactional
public class UserFacadeDao {

    private final EntityManager em;

    @Autowired
    public UserFacadeDao(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public User createUser(User user){
        em.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(User user) {
        return em.merge(user);
    }

    @Transactional
    public User findUserByLogin(String login) {
        em.getTransaction().begin();
        User user = (User) em.createQuery("from User where login =:login")
                .setParameter("login", login).getSingleResult();
        em.getTransaction().commit();
        return user;
    }

    @Transactional
    public void removeUser(User user) {
        UserInfo info = findUserByLogin(user.getLogin()).getInfo();
        em.remove(info);
        em.remove(user);
    }
}
