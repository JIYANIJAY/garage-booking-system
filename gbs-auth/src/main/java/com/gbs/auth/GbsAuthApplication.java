package com.gbs.auth;

import com.gbs.common.entity.Address;
import com.gbs.common.entity.User;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {User.class, Address.class})
@EnableRabbit
public class GbsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbsAuthApplication.class, args);
    }

}
