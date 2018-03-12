package ru.aparc.facadeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aparc.domain.Post;
import ru.aparc.domain.User;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class PostFacadeDao {

    private final EntityManager em;

    @Autowired
    public PostFacadeDao(EntityManager em) {
        this.em = em;
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

        em.getCriteriaBuilder();
        return findUserByLogin(login).getPostList();
    }

    public List<Post> getAllPosts() {
        return em.createQuery("from Post").getResultList();
    }

    public List<Post> getPostsByQuery(String location, Double price) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Post> postCriteriaQuery = cb.createQuery(Post.class);
        Root<Post> post = postCriteriaQuery.from(Post.class);
        Path<Double> path = post.<Double> get("price");
        postCriteriaQuery.select(post);
        if (location.length() != 0 && price != 0) {
            postCriteriaQuery.where(cb.and(cb.equal(post.get("location"), location),
                    cb.lessThanOrEqualTo(path, price)));
        } else if (location.length() != 0 && price == 0) {
            postCriteriaQuery.where(cb.equal(post.get("location"), location));
        } else if (location.length() == 0 && price != 0) {
            postCriteriaQuery.where(cb.lessThanOrEqualTo(path, price));
        }
        return em.createQuery(postCriteriaQuery).getResultList();
    }

    public void postInTransaction(String login, Post post) {
        em.getTransaction().begin();
        try {
            User user = findUserByLogin(login);
            if (user == null) throw new IllegalStateException("No root user");

            post.setUser(user);
//            user.setPostList(post);

            em.persist(post);
            em.refresh(user);

            em.getTransaction().commit();
        } catch (Throwable e) {
            em.getTransaction().rollback();
        }
    }
}
