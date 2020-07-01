package com.liuhang.springdatajpa.dao;

import com.liuhang.springdatajpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 符合 springDataJap 规范的dao接口
 *          1.继承 JpaRepository，第一个泛型为操作的实体类，第二个泛型为主键的类型，
 *                封装了基本的CRUD操作
 *          2.继承 JpaSpecificationExecutor，泛型为操作的实体类
 *                封装了复杂的查询（分页）
 * 符合 SpringDataJpa 规范的接口，查询方法有如下几种：
 *          方式一：使用接口继承的两个接口中定义好的通用方法。接口不需要实现类
 *          方式二：使用jpql语句进行查询
 *          方式三：使用sql语句进行查询
 *          方式四：方法名称规则查询
 *                  是对jpql的一种封装，只需要按照springdataJpa提供的方法名称规则定义方法，不需要配置jpql，即可完成查询
 */
public interface CustomerDaoJPQL extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    // ===================================jpql查询====================================
    /**
     * 测试jpql语句查询：
     *          jpql：jpa query language 即 jpa的查询语句
     *          ?n：表示当前的占位符应该去参数列表中n个参数。参数从1开始
     */
    @Query("from Customer where custName like ?1")
    List<Customer> findByName(String custName);

    @Query("from Customer where custName like ?1 and custId = ?2")
    Customer findByNameAndCustId(String custName, Long custId);

    /**
     * 使用jpql语句进行更新操作
             * @Query 注解是标志查询操作的，所以当使用该注解进行更新操作时，需要添加@Modifying注解进行标志
     */
    @Query("update Customer set custName = ?2 where custId = ?1")
    @Modifying
    Integer updateNameById(Long custId, String custName);

}
