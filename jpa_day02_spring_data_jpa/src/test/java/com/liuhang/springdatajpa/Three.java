package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.CustomerDaoSQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Three {

    /**
     * 测试查询方式三：SQL语句查询
     */

    @Autowired
    private CustomerDaoSQL customerDaoSQL;

    @Test
    public void testFindSql() {
        List<Object[]> allBySql = customerDaoSQL.findAllBySql();
        allBySql.forEach(object -> System.out.println(Arrays.toString(object)));
    }

    @Test
    public void testFindSqlByName() {
        List<Object[]> allBySql = customerDaoSQL.findAllByNameSql("%方法");
        allBySql.forEach(object -> System.out.println(Arrays.toString(object)));
    }
}
