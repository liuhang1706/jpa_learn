package com.liuhang.jpa;

import com.liuhang.jpa.bean.Customer;
import com.liuhang.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 测试jpa的基本单表增删改查
 *      都是根据主键进行查询、修改、删除
 */
public class One {

    @Test
    public void testSaveCustomer() {
        // 1.根据配置文件获取实体类管理器工厂
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        // 2.从工厂获取实体类管理器
        EntityManager entityManager = factory.createEntityManager();
        // 3.从实体类管理器获取事务
        EntityTransaction tx = entityManager.getTransaction();
        // 4.开启事务
        tx.begin();

        // 5.执行保存操作
        Customer customer = new Customer();
        customer.setCustName("张三");
        customer.setCustIndustry("攻城狮");
        entityManager.persist(customer);

        // 6.事务提交
        tx.commit();
        // 7.关闭资源
        entityManager.close();
        factory.close();
    }

    @Test
    public void testSaveCustomerFromJpaUtils() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 3.从实体类管理器获取事务
        EntityTransaction tx = entityManager.getTransaction();
        // 4.开启事务
        tx.begin();
        // 5.执行保存操作
        Customer customer = new Customer();
        customer.setCustName("张三");
        customer.setCustIndustry("律师");
        entityManager.persist(customer);
        // 6.事务提交
        tx.commit();
        // 7.关闭资源
        entityManager.close();
        JpaUtils.closeResource();
    }

    /**
     * find方法：根据ID查询数据
     *     1.执行find方法，则立即发送SQL请求数据库
     *     2.find方法返回的是实体类对象
     */
    @Test
    public void testFind() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 2L);
        System.out.println(customer);
        System.out.println(customer.getClass());

        transaction.commit();

        entityManager.close();
        JpaUtils.closeResource();

    }

    /**
     * getReference方法：
     *        1.执行getReference方法不会立即发送SQL，而是等到需要使用查询结果时才真正进行查询
     *        2.getReference方法返回的对象是一个动态代理对象
     */
    @Test
    public void testGetReference() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = entityManager.getReference(Customer.class, 2L);

        transaction.commit();
        System.out.println(customer);
        System.out.println(customer.getClass());
        entityManager.close();
        JpaUtils.closeResource();
    }

    /**
     * remove方法
     */
    @Test
    public void testRemove() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 查询要删除的对象
        Customer customer = entityManager.getReference(Customer.class, 1L);
        System.out.println(customer);
        // 删除操作
        entityManager.remove(customer);

        transaction.commit();
        entityManager.close();
        JpaUtils.closeResource();
    }

    /**
     * merge方法
     */
    @Test
    public void testUpdate() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 查询要更新的对象
        Customer customer = entityManager.getReference(Customer.class, 2L);
        System.out.println(customer);
        customer.setCustName("lisi");
        // 更新操作
        entityManager.merge(customer);

        transaction.commit();
        entityManager.close();
        JpaUtils.closeResource();
    }
}
