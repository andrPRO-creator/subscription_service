package com.andrPRO_creator.subscription_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequest {
    private String username;
    private Set<Long> subscriptionsId;


}
