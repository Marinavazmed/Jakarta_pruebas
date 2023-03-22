package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
	private static final EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("jakarta_ee_hibernate");

	
	private static EntityManager getEntityManager() {
		return  managerFactory.createEntityManager();
	}
}
