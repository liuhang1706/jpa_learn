package com.liuhang.jpa;

import com.liuhang.jpa.bean.Customer;
import com.liuhang.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * 测试 JPQL，类似于Hibernate的HQL语句
 */
public class Two {

    /**
     * 查询全部：
     *      sql：select * from cst_customer
     *      jpql：from com.liuhang.jpa.bean.Customer
     */
    @Test
    public void testFindAll() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 3.从实体类管理器获取事务
        EntityTransaction tx = entityManager.getTransaction();
        // 4.开启事务
        tx.begin();

        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        List<Customer> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        // 6.事务提交
        tx.commit();
        // 7.关闭资源
        entityManager.close();
        JpaUtils.closeResource();
    }

    /**
     * 测试排序：
     *       sql：select * from cst_customer order by cust_id;
     *       jpql：from Customer order by custId
     */
    @Test
    public void testOrder() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 3.从实体类管理器获取事务
        EntityTransaction tx = entityManager.getTransaction();
        // 4.开启事务
        tx.begin();

        String jpql = "from Customer order by custId desc ";
        Query query = entityManager.createQuery(jpql);
        List<Customer> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        // 6.事务提交
        tx.commit();
        // 7.关闭资源
        entityManager.close();
        JpaUtils.closeResource();
    }

    /**
     * 测试查询 count：
     *      sql：select count(cust_id) from cst_customer;
     *      jpql：select count(custId) from Customer
     */
    @Test
    public void testCount() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 3.从实体类管理器获取事务
        EntityTransaction tx = entityManager.getTransaction();
        // 4.开启事务
        tx.begin();

        String jpql = "select count(custId) from Customer ";
        Query query = entityManager.createQuery(jpql);
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);

        // 6.事务提交
        tx.commit();
        // 7.关闭资源
        entityManager.close();
        JpaUtils.closeResource();
    }

    /**
     * 测试分页查询：
     *          sql：select * from cst_customer limit ?,?
     *          jpql：from Customer
     */
    @Test
    public void testPage() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 3.从实体类管理器获取事务
        EntityTransaction tx = entityManager.getTransaction();
        // 4.开启事务
        tx.begin();

        String jpql = "from Customer ";
        Query query = entityManager.createQuery(jpql);
        // 设置参数
        query.setFirstResult(0);
        query.setMaxResults(2);
        List<Customer> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        // 6.事务提交
        tx.commit();
        // 7.关闭资源
        entityManager.close();
        JpaUtils.closeResource();
    }

    /**
     * 测试条件查询：
     *          sql：selct * from cst_customer where cust_name like '%张三%'
     *          jpql：from Customer where custName like ?
     */
    @Test
    public void testCondition() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 3.从实体类管理器获取事务
        EntityTransaction tx = entityManager.getTransaction();
        // 4.开启事务
        tx.begin();

        String jpql = "from Customer where custName like ?1";
        Query query = entityManager.createQuery(jpql);
        // 设置参数
        query.setParameter(1,"%张三%");
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);

        // 6.事务提交
        tx.commit();
        // 7.关闭资源
        entityManager.close();
        JpaUtils.closeResource();
    }

}
