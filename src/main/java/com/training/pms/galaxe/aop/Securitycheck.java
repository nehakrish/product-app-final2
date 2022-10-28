package com.training.pms.galaxe.aop;



//import java.sql.Time;
import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
public class Securitycheck {

	@Before(value =  "execution(* com.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void checkin() {
		System.out.println("###check in at :"+new Date() + " By Aspects");
	}

	@After(value =  "execution(* com.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void checkout() {
		System.out.println("###Check out at :"+new Date()+ " By Aspects");
	}
}
