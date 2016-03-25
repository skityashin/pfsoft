package com.model;

import javax.persistence.*;

/**
 * Class {@link User}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */

// the creation of the database columns

@Entity
@Table(name = "user")

public class User {

    private long id;
    private String pass;
    private String login;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
