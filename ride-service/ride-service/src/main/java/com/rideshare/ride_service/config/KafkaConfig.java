package com.rideshare.ride_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    // topic where ride service publshes ride request
    // matching service subcribes to the topic

    @Bean
    public NewTopic rideRequestedTopic () {


        return TopicBuilder.name("ride.requested").partitions(3)
                .replicas(1)
                .build();
    }


    // topic where matchin service published match result



    @Bean
    public NewTopic rideMatchedTopic() {

        return TopicBuilder.name("ride.matched")
                .partitions(3)
                .replicas(1).build();
    }


}
