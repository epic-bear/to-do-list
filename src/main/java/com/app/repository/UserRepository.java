package com.app.repository;

import com.app.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }
}
