package com.repository;

import com.model.User;

/**
 * Class {@link UserRepository}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */

public interface UserRepository {

    User findByLogin (String login);

}
