package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.CustomerDao;
import com.liuhang.springdatajpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class One {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 使用接口继承的两个接口中定义好的通用方法。接口不需要实现类
     *  CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer>
     *      JpaRepository 接口封装了简单的CRUD操作
     *      JpaSpecificationExecutor 接口封装了复杂查询操作（分页）
     */

    /**
     * JpaRepository接口的 findOne 根据ID查询
     */
    @Test
    public void testFindOne() {
        Customer one = customerDao.findOne(3L);
        System.out.println(one);
    }

    /**
     * JpaRepository接口的 save方法表示更新或者插入：
     *          更新：
     *                 传入的对象主键属性不为null，则表示更新。注意：更新操作最好是先查询再更新，防止将其他字段更新成null
     *                 同时更新时，会先查询数据库中是否存在此记录，如果不存在，则自动转换成insert操作
     *          插入：传入的对象主键属性为null，则表示插入
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustId(3L);
        customer.setCustName("测试save2");
        Customer one = customerDao.save(customer);
        System.out.println(one);
    }
    @Test
    public void testSave1() {
        Customer customer = new Customer();
        customer.setCustName("测试save2");
        Customer one = customerDao.save(customer);
        System.out.println(one);
    }

    /**
     * JpaRepository接口的 delete方法表示根据ID删除
     */
    @Test
    public void testDelete() {
        customerDao.delete(5L);
    }

    /**
     * JpaRepository接口的 delete方法表示根据ID删除
     */
    @Test
    public void testFindAll() {
        List<Customer> all = customerDao.findAll();
        all.forEach(System.out::println);
    }


    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println(count);
    }

    @Test
    public void testExist() {
        boolean exists = customerDao.exists(11L);
        System.out.println(exists);
    }

    /**
     * getOne和findOne：都是根据主键获取对象
     *          getOne：em.getReference()。返回的是对象的动态代理对象，同时只在使用返回结果时进行查询
     *                  为了让getOne方法正常执行，需要使用@Transactional注解进行标记
     *          findOne：em.find()。返回是真正的实体类对象，一经调用立即进行查询
     */
    @Test
    @Transactional
    public void testGetOne() {
        Customer one = customerDao.getOne(3L);
        System.out.println(one);
    }
}
