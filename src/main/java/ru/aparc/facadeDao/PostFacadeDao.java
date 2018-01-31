package ru.aparc.facadeDao;

import ru.aparc.domain.Post;
import ru.aparc.domain.User;

import javax.persistence.*;
import java.util.Date;

public class PostFacadeDao {

    final EntityManager em;

    public PostFacadeDao(EntityManager em) {
        this.em = em;
    }

    public User createUser() throws EntityExistsException{
        User user = new User();
        user.setLogin("admin");
        user.setPassword("root");

        em.persist(user);
        return user;
    }

    public User findUserById(int id){
        try {
            User user = em.find(User.class, id);
        return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void postInTransaction(int id, String text) {
        em.getTransaction().begin();
        try {
            User user = findUserById(id);
            if (user == null) throw new IllegalStateException("No root user");

            Post post = new Post();
            post.setDate(new Date());
            post.setText(text);
            post.setUser(user);

            em.persist(post);
            em.refresh(user);

            em.getTransaction().commit();
        } catch (Throwable e) {
            em.getTransaction().rollback();
        }
    }
}
