package com.service;

import com.model.User;

/**
 * Class {@link UserService}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */
public interface UserService {

    User findByLogin (String login);
}
