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
import java.util.List;

@ApplicationScoped
public class ActionService {

    @Inject
    ActionRepository actionRepository;

    @Inject
    UserService userService;

    public List<Action> listAll() {
        return actionRepository.listAll();
    }

    @Transactional
    public Action updateAction(Long id, Action updatedAction) {
        Action existingAction = actionRepository.findById(id);
        if (existingAction == null) {
            return null;
        }
        existingAction.setDescription(updatedAction.getDescription());
        existingAction.setStatus(updatedAction.getStatus());
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
