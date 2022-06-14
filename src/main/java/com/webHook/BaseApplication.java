package com.webHook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author mazixi
 */
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.webHook.*"})
public class BaseApplication {

	public static void main(String[] args) {

		SpringApplication.run(com.webHook.BaseApplication.class, args);

	}

}
