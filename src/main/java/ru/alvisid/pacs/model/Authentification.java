package ru.alvisid.pacs.model;

import javax.persistence.*;

/**
 * Сущность хранит логин и пароль, необходимый сущности VirtualUser
 * для получения ролей.
 * */
@Entity
@Table(name = "auth")
public class Authentification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
