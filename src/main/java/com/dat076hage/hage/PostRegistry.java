package com.dat076hage.hage;

import com.dat076hage.hage.model.Post;
import com.dat076hage.hage.model.User;
import com.dat076hage.hage.persistence.AbstractDAO;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PostRegistry extends AbstractDAO<Post, Long> {

    @PersistenceContext(unitName = "hage_pu")
    private EntityManager em;

    public PostRegistry() {
        super(Post.class);
    }

    // Global posts
    public List<Post> getPosts(int from, int to) {
        Query q = em.createQuery("SELECT p FROM Post p ORDER BY p.date", Post.class);
        q.setFirstResult(from);
        q.setMaxResults(to - from);
        return q.getResultList();
    }
    
    // Local posts
    public List<Post> getPosts(int from, int to, double lat, double lon) {
        Query q = em.createQuery("SELECT p FROM Post p ORDER BY p.date", Post.class);
        q.setFirstResult(from);
        q.setMaxResults(to - from);
        return q.getResultList();
    }

    // User specific posts
    public List<Post> getPosts(int from, int to, User user) {
        List<Post> posts = new ArrayList<>();
        for (User u : user.getFollowing()) {
            posts.addAll(em.createQuery("SELECT p FROM Post p WHERE p.user = :user ORDER BY p.date", Post.class).setParameter("user", u).getResultList());
        }
        return posts;
    }
    
    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}