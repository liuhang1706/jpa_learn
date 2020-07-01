package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.UserDao;
import com.liuhang.springdatajpa.domain.Role;
import com.liuhang.springdatajpa.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private UserDao userDao;

    /**
     * 配置用户维护外键关系，其用户侧配置了级联操作
     *      测试添加：用户的插入将触发角色的插入，且由用户维护级联操作
     */
    @Test
    public void testAdd() {
        User user = new User();
        user.setUserName("小张");
        Role role = new Role();
        role.setRoleName("校长");
        user.getRoleSet().add(role);
        role.getUserSet().add(user);
        userDao.save(user);
    }
    /**
     * 配置用户维护外键关系，其用户侧配置了级联操作
     *      测试级联删除：将删除指定用户及其关联的角色
     *      记得将配置文件中的hibernate.hbm2ddl.auto改成update
     */
    @Test
    public void testDel() {
        User user = userDao.findOne(1L);
        System.out.println(user);
        userDao.delete(user);
    }

}
