package com.mywebapp.dao;

import com.mywebapp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUser() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void deleteUserById(long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class).setParameter("username", username).getSingleResult();
    }
}
