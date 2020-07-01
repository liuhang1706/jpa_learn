package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.CustomerDao;
import com.liuhang.springdatajpa.dao.LinkManDao;
import com.liuhang.springdatajpa.domain.Customer;
import com.liuhang.springdatajpa.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户，保存一个联系人
     * 客户和联系人分别插入表中
     * 但是因为没有设置客户和联系人的关系，所以联系人的外键为null
     *
     * 同时操作两张表，需要添加事务
     * 添加了事务，默认是自动回滚的，所以需要手动关闭自动回滚
     */
    @Test
    @Transactional
    @Rollback(false)
    public void demo01() {
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan man = new LinkMan();
        man.setLkmName("link1");

        customerDao.save(customer);
        linkManDao.save(man);
    }

    /**
     * 保存一个客户，保存一个联系人
     * 客户和联系人分别插入表中
     * 在客户侧设置了关联关系，又客户维护了外键，所以联系人表中的外键引用客户表中的主键
     */
    @Test
    @Transactional
    @Rollback(false)
    public void demo02() {
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan man = new LinkMan();
        man.setLkmName("link1");

        // 设置关系
        customer.getManSet().add(man);

        customerDao.save(customer);
        linkManDao.save(man);
    }

    /**
     * 保存一个客户，保存一个联系人
     * 客户和联系人分别插入表中
     * 在联系人侧设置了关联关系，又联系人维护了外键，所以联系人表中的外键引用客户表中的主键
     */
    @Test
    @Transactional
    @Rollback(false)
    public void demo03() {
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan man = new LinkMan();
        man.setLkmName("link1");

        // 设置关系
        man.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(man);
    }

    /**
     * 保存一个客户，保存一个联系人
     * 客户和联系人分别插入表中
     * 在联系人侧设置了关联关系，又联系人维护了外键，所以联系人表中的外键引用客户表中的主键
     * 在客户侧设置了关联关系，又客户维护了外键，所以联系人表中的外键引用客户表中的主键
     *      如果双方都维护外键，将导致多余的update语句
     *      所以通常让只让多的一方维护外键，一的一方放弃维护外键
     */
    @Test
    @Transactional
    @Rollback(false)
    public void demo04() {
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan man = new LinkMan();
        man.setLkmName("link1");

        // 设置关系
        customer.getManSet().add(man);
        man.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(man);
    }



    /**
     * 配置多的一方维护外键关系，并在一的一方配置级联添加
     *      测试级联添加
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("linname");

        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("linname1");

        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("linname2");

        customer.getManSet().add(linkMan);
        customer.getManSet().add(linkMan1);
        customer.getManSet().add(linkMan2);

        linkMan.setCustomer(customer);
        linkMan1.setCustomer(customer);
        linkMan2.setCustomer(customer);

        customerDao.save(customer);
    }
    /**
     * 配置多的一方一方维护外键关系，一的一方配置级联操作
     *      测试级联删除
     *      记得将配置文件中的hibernate.hbm2ddl.auto改成update
     */
    @Test
    public void testDel() {
        Customer one = customerDao.findOne(1L);
        customerDao.delete(one);
    }

}
