package com.andrPRO_creator.subscription_service.service;

import com.andrPRO_creator.subscription_service.dto.request.UserRequest;
import com.andrPRO_creator.subscription_service.entity.User;
import com.andrPRO_creator.subscription_service.exception.AlreadyExistsException;
import com.andrPRO_creator.subscription_service.exception.NotFoundException;
import com.andrPRO_creator.subscription_service.repository.SubscriptionRepository;
import com.andrPRO_creator.subscription_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public User getUser(Long id) {
        User user =  getUserOrThrowException(id);
        logger.info("User with ID {} is get", id);
        return user;
        }



    public void addUser(UserRequest request) {
        alreadyExistUsername(request);

        User user = new User();
        user.setUsername(request.getUsername());

        userRepository.saveAndFlush(user);
        logger.info("A user {} has been created with the ID: {}",user.getUsername(), user.getId());
    }

    public void updateUser(Long id, UserRequest request) {
        User user = getUserOrThrowException(id);

        if (request.getUsername() != null){
            alreadyExistUsername(request);
            user.setUsername(request.getUsername());
        }
        logger.info("A user with ID {} has been updated", id);
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
        logger.info("A user with ID {} has been deleted", id);
    }


    public void alreadyExistUsername(UserRequest request){
        userRepository
                .findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new AlreadyExistsException(String.format
                            ("Username %s is already exist", request.getUsername()));
                });
    }


    public User getUserOrThrowException(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User with id %s doesn't exist", id)));
    }
}





