package com.liuhang.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {

    private volatile static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        if (factory == null) {
            synchronized (JpaUtils.class) {
                if (factory == null) {
                    factory = Persistence.createEntityManagerFactory("myJpa");
                }
            }
        }
        return factory.createEntityManager();
    }

    public static void closeResource() {
        if (factory != null) {
            synchronized (JpaUtils.class) {
                if (factory != null) {
                    factory.close();
                }
            }
        }
    }
}
