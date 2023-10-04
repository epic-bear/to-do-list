package com.app.service;

import com.app.domain.Action;
import com.app.domain.Status;
import com.app.repository.ActionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ActionService {

    @Inject
    ActionRepository actionRepository;

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

    public void createAction(String username, String description) {
//
//        action.setStatus(Status.NEW);
//        actionRepository.persist(action);
    }
}
