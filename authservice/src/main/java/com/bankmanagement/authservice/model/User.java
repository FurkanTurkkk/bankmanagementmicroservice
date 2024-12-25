package com.bankmanagement.authservice.model;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;

    private boolean AccountNonExpired;
    private boolean AccountNonLocked;
    private boolean CredentialsNonExpired;
    private boolean Enabled;

    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name = "authorities",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

    public User() {
    }

    public User(String name,
                String username,
                String password,
                boolean accountNonLocked,
                boolean accountNonExpired,
                boolean credentialsNonExpired,
                boolean enabled,
                Set<Role> authorities) {
        this.name = name;
        this.username = username;
        this.password = password;
        AccountNonLocked = accountNonLocked;
        AccountNonExpired = accountNonExpired;
        CredentialsNonExpired = credentialsNonExpired;
        Enabled = enabled;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
