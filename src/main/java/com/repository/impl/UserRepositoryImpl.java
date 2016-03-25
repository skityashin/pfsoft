package com.repository.impl;

import com.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.repository.UserRepository;
import org.springframework.util.CollectionUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Class {@link UserRepositoryImpl}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByLogin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login");
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }
}

