package ru.alvisid.pacs.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Сущность служит для входа в личный кабинет и хранит роли, пароль и логин.
 * */
@Entity
@Table(name = "virtual_user")
public class VirtualUser extends User {
    @OneToOne(cascade = CascadeType.ALL)
    Authentification auth;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Transient
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public Authentification getAuth() {
        return auth;
    }

    public void setAuth(Authentification auth) {
        this.auth = auth;
    }
}
