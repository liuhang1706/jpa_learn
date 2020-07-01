package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.CustomerDao;
import com.liuhang.springdatajpa.dao.LinkManDao;
import com.liuhang.springdatajpa.domain.Customer;
import com.liuhang.springdatajpa.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class QueryTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试对象导航查询：直接调用对象的get方法，即可获取对应的属性对象
     *     注意修改hibernate.hbm2ddl.auto的值为update
     *     当从一的一方（Customer）查多的一方（LinkMan）时，默认是延时加载，即什么时候使用什么时候查询
     *     当从多的一方（LinkMan）查一的一方（Customer）时，默认是立即加载，即调用get方法就进行查询
     *     延时加载和立即加载可以通过@OneToMany等这样的注解中配置fetch属性进行修改
     */
    @Test
    @Transactional
    public void testDel() {
        Customer customer = customerDao.findOne(1L);
        Set<LinkMan> manSet = customer.getManSet();
        manSet.forEach(System.out::println);
    }
    /**
     * 测试对象导航查询
     *     注意修改hibernate.hbm2ddl.auto的值为update
     */
    @Test
    @Transactional
    public void testDe2l() {
        LinkMan one = linkManDao.findOne(1L);
        Customer customer = one.getCustomer();
        System.out.println(customer);
    }

}
