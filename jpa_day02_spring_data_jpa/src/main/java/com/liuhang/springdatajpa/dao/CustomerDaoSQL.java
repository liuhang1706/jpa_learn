package com.liuhang.springdatajpa.dao;

import com.liuhang.springdatajpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface CustomerDaoSQL extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    // ===================================sql查询====================================

    /**
     * 使用sql语句进行查询，返回值必须为List<Object[]>,
     *              nativeQuery：表示当前的查询是否为本地查询（SQL查询）
     *                           默认为false，表示为jpql查询
     *                           当为true时，表示为SQL查询
     * @return
     */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Object[]> findAllBySql();

    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    List<Object[]> findAllByNameSql(String name);

}
