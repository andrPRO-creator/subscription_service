package com.andrPRO_creator.subscription_service.repository;

import com.andrPRO_creator.subscription_service.dto.response.PopularSubDto;
import com.andrPRO_creator.subscription_service.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByName(String name);

    @Query(nativeQuery = true, value = """
        SELECT 
            s.id as subId,
            s.name as subName,
            COUNT(us.user_id) as userCount
        FROM subscriptions s
        LEFT JOIN user_subscriptions us ON s.id = us.subscription_id
        GROUP BY s.id
        ORDER BY userCount DESC
        LIMIT 3
        """)
    List<PopularSubDto> findTop3PopularSubscriptions();
}
