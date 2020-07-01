package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.CustomerDaoMethodName;
import com.liuhang.springdatajpa.dao.CustomerDaoSQL;
import com.liuhang.springdatajpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Four {

    /**
     * 方式四：方法名称规则查询
     */

    @Autowired
    private CustomerDaoMethodName customerDaoMethodName;

    @Test
    public void testFindByName() {
        List<Customer> allBySql = customerDaoMethodName.findByCustName("测试save2");
        allBySql.forEach(System.out::println);
    }

    @Test
    public void testFindByNameLike() {
        List<Customer> allBySql = customerDaoMethodName.findByCustNameLike("%测试%");
        allBySql.forEach(System.out::println);
    }
    @Test
    public void testFindByNameLikeAndId() {
        List<Customer> allBySql = customerDaoMethodName.findByCustNameLikeAndCustId("%测试%",3L);
        allBySql.forEach(System.out::println);
    }
}
