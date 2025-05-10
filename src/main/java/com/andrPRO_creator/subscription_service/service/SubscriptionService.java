package com.andrPRO_creator.subscription_service.service;

import com.andrPRO_creator.subscription_service.dto.request.SubscriptionRequest;
import com.andrPRO_creator.subscription_service.dto.response.PopularSubDto;
import com.andrPRO_creator.subscription_service.entity.Subscription;
import com.andrPRO_creator.subscription_service.entity.User;
import com.andrPRO_creator.subscription_service.exception.AlreadyExistsException;
import com.andrPRO_creator.subscription_service.exception.NotFoundException;
import com.andrPRO_creator.subscription_service.repository.SubscriptionRepository;
import com.andrPRO_creator.subscription_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);


    public Subscription getSubscription(Long id) {
        Subscription sub = getSubscriptionOrElseThrow(id);
        logger.info("Subscription with ID {} is get", id);
        return sub;
    }


    public void addSubscription(SubscriptionRequest subscriptionRequest) {
        alreadyExistSubscription(subscriptionRequest);
        Subscription sub = new Subscription();
        sub.setName(subscriptionRequest.getName());
        subscriptionRepository.saveAndFlush(sub);
        logger.info("A subscription {} has been created with the ID: {}",sub.getName(), sub.getId());
    }



    public void addUserSub(Long userId, Long subId) {
        User user = getUserOrThrowException(userId);
        Subscription subscription = getSubscription(subId);
        user.addSubscription(subscription);
        userRepository.save(user);
        logger.info("A subscription with ID: {} added to user with ID: {}",subId, userId);
    }

    @Transactional
    public void deleteUserSub(Long userId, Long subId) {
        User user = getUserOrThrowException(userId);
        Subscription subscription = getSubscription(subId);

        user.getSubscriptions().remove(subscription);
        subscription.getUsers().remove(user);
        logger.info("A subscription with ID: {} deleted to user with ID: {}",subId, userId);
    }

    public void deleteSub(Long id) {
        getSubscriptionOrElseThrow(id);
        subscriptionRepository.deleteById(id);
        logger.info("A subscription with ID: {} was been deleted", id);

    }

    public Subscription getSubscriptionOrElseThrow(Long id){
        return subscriptionRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Subscription with id %s doesn't exist", id)));
    }

    public void alreadyExistSubscription(SubscriptionRequest request){
        subscriptionRepository
                .findByName(request.getName())
                .ifPresent(subscription -> {
                    throw new AlreadyExistsException(String.format(
                            "Subscription %s is already exist", request.getName()
                    ));
                });
    }

    public User getUserOrThrowException(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User with id %s doesn't exist", id)));
    }

    public List<PopularSubDto> getTop3Subs() {
        return subscriptionRepository
                .findTop3PopularSubscriptions();
    }
}

