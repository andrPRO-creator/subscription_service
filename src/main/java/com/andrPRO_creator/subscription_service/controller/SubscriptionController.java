package com.andrPRO_creator.subscription_service.controller;

import com.andrPRO_creator.subscription_service.dto.request.SubscriptionRequest;
import com.andrPRO_creator.subscription_service.dto.response.PopularSubDto;
import com.andrPRO_creator.subscription_service.entity.Subscription;
import com.andrPRO_creator.subscription_service.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long id) {
        logger.info("Requesting a subscription with an ID: {}", id);
        Subscription subscription = subscriptionService.getSubscription(id);
        return ResponseEntity.ok(subscription);
    }

    @PostMapping
    public ResponseEntity<Void> addSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        logger.info("Request to create a new subscription");
        subscriptionService.addSubscription(subscriptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        logger.info("Request to delete subscription with an ID: {}", id);
        subscriptionService.deleteSub(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/{subId}")
    public ResponseEntity<Void> addUserSub(@PathVariable Long userId,
                                           @PathVariable Long subId) {
        logger.info("Request to add a subscription ID: {}, to a user ID: {}", subId, userId);
        subscriptionService.addUserSub(userId, subId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userId}/{subId}")
    public ResponseEntity<Void> deleteUserSub(@PathVariable Long userId,
                                              @PathVariable Long subId) {
        logger.info("Request to delete subscription ID: {} from user ID: {}", subId, userId);
        subscriptionService.deleteUserSub(userId, subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top3")
    public ResponseEntity<List<PopularSubDto>> getTop3() {
        logger.info("Request to get top 3 popular subscription");
        List<PopularSubDto> topSubs = subscriptionService.getTop3Subs();
        return ResponseEntity.ok(topSubs);
    }
}