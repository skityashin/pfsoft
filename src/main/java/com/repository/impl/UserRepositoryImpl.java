package com.repository.impl;

import com.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return entityManager.find(User.class, login);
    }
}
