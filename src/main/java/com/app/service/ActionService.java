package com.app.service;

import com.app.domain.Action;
import com.app.domain.Status;
import com.app.domain.User;
import com.app.dto.ActionDTO;
import com.app.repository.ActionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
public class ActionService {

    @Inject
    ActionRepository actionRepository;

    @Inject
    UserService userService;

    @Transactional
    public Action updateAction(Long id, ActionDTO actionDTO) {
        Action existingAction = actionRepository.findById(id);
        if (existingAction == null) {
            return null;
        }
        existingAction.setDescription(actionDTO.getDescription());
        existingAction.setStatus(Status.valueOf(actionDTO.getStatus()));
        actionRepository.persist(existingAction);
        return existingAction;
    }

    public void createAction(ActionDTO actionDTO) {
        User user = userService.getUser(actionDTO.getUsername());
        Action action = new Action();
        action.setStatus(Status.NEW);
        action.setDescription(actionDTO.getDescription());
        action.setCreatedDate(LocalDateTime.now());
        action.setUser(user);
        actionRepository.persist(action);
    }
}
