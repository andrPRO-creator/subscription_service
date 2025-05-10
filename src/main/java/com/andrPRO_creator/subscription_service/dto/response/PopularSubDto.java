package com.andrPRO_creator.subscription_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PopularSubDto {
    Long subId;
    String subName;
    Long userCount;
}
