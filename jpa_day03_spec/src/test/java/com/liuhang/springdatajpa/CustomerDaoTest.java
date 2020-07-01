package com.liuhang.springdatajpa;

import com.liuhang.springdatajpa.dao.CustomerDao;
import com.liuhang.springdatajpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * Specification 表示封装了查询的条件：
     *              Root<Customer> root：查询的根对象（查询的任何属性都可以从根对象中获取）
     *              CriteriaQuery<?> query：顶层查询对象，自定义查询方式（了解，一般不用）
     *              CriteriaBuilder cb：查询构造器，封装了很多的查询条件
     */


    @Test
    public void testPreOneCondition() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                // equal表示精准匹配
                Predicate predicate = cb.equal(custName, "测试save2");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    @Test
    public void testPreTwoCondition() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate1 = cb.equal(custName, "测试save2");

                Path<Object> custId = root.get("custId");
                Predicate predicate2 = cb.equal(custId, 3L);

                Predicate and = cb.and(predicate1, predicate2);
                return and;
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * Specification：like查询
     */
    @Test
    public void testPreConditionLike() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate1 = cb.like(custName.as(String.class), "%测试%");
                return predicate1;
            }
        };
        List<Customer> customers = customerDao.findAll(specification);
        customers.forEach(System.out::println);
    }

    /**
     * Specification：like查询+排序
     */
    @Test
    public void testPreConditionLikeOrder() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate1 = cb.like(custName.as(String.class), "%测试%");
                return predicate1;
            }
        };
        Sort orders = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> customers = customerDao.findAll(specification, orders);
        customers.forEach(System.out::println);
    }

    /**
     * Specification：like查询+分页
     */
    @Test
    public void testPreConditionLikePage() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate1 = cb.like(custName.as(String.class), "%测试%");
                return predicate1;
            }
        };
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> all = customerDao.findAll(specification, pageable);
        System.out.println("totalElement = " +all.getTotalElements());
        System.out.println("pages = " +all.getTotalPages());
        all.getContent().forEach(System.out::println);
    }
}
