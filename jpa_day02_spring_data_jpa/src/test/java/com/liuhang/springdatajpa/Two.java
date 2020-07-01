package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.CustomerDaoJPQL;
import com.liuhang.springdatajpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Two {
    /**
     * 测试查询方式二：JPQL语句查询
     */

    @Autowired
    private CustomerDaoJPQL customerDaoJPQL;

    @Test
    public void testGetOne() {
        List<Customer> customers = customerDaoJPQL.findByName("测试%");
        customers.forEach(System.out::println);
    }

    @Test
    public void testFindByNameAndId() {
        Customer customer = customerDaoJPQL.findByNameAndCustId("测试%", 3L);
        System.out.println(customer);
    }

    /**
     * 使用jpql语句进行更新/删除操作：
     *              1.需要手动添加事务
     *              2.默认情况下，springDataJpa会回滚事务
     * @Rollback 表示事务是否自动回滚，默认为true
     */
    @Test
    @Rollback(value = false)
    @Transactional
    public void testUpdateNameById() {
        Integer result = customerDaoJPQL.updateNameById(3L, "测试update方法");
        System.out.println(result);
    }

}
