package com.app.service;

import com.app.domain.Action;
import com.app.domain.User;
import com.app.repository.ActionRepository;
import com.app.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    ActionRepository actionRepository;

    @Transactional
    public User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        userRepository.persist(user);
        return user;
    }

    public List<Action> getUserActions(String username) {
        User user = getUser(username);
        return actionRepository.findActionsByUser(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
