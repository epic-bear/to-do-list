package com.app.repository;

import com.app.domain.Action;
import com.app.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ActionRepository implements PanacheRepositoryBase<Action, Long> {

    public List<Action> findActionsByUser(User user) {
        return find("user", user).list();
    }
}
