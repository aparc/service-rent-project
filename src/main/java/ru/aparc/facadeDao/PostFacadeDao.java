package ru.aparc.facadeDao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.aparc.domain.Post;
import ru.aparc.domain.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    public User findUserByLogin(String login){
        try {
            User user = (User) em.createQuery("from User where login =:login")
                    .setParameter("login", login)
                    .getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Post> getUserPosts(String login) {
        return findUserByLogin(login).getPostList();
    }

    public List<Post> getAllPosts() {
        return em.createQuery("from Post").getResultList();
    }

    public void postInTransaction(String login, String text) {
        em.getTransaction().begin();
        try {
            User user = findUserByLogin(login);
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
