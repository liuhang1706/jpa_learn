package com.liuhang.springdatajpa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体类
 * 配置映射关系：
 *          1.实体类与数据库表的映射
 *              @Entity和@Table
 *          2.实体类属性与表字段的映射
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    /**
     * @Id 标志主键
     * @GeneratedValue 表示主键生成策略
     *                  GenerationType.IDENTITY：自增，必须要底层数据库支持自增。比如：MySQL
     *                  GenerationType.SEQUENCE：序列，必须要底层数据库支持序列。比如：Oracle
     *                  GenerationType.TABLE：jpa提供的一种机制，通过创建一张数据库表的形式帮助完成主键自增。仅仅了解
     *                  GenerationType.AUTO：自动，jpa根据当前环境自动决定使用何种主键生成策略。仅仅了解
     * @Column 配置属性和字段的映射关系
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;

    @Column(name = "cust_name")
    private String custName;
    /**
     * 客户来源
     */
    @Column(name = "cust_source")
    private String custSource;
    /**
     * 客户所属行业
     */
    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name = "cust_phone")
    private String custPhone;

    /**
     * 一对多配置 方法一：
     *      配置customer和linkMan的包含关系（一对多） @OneToMany
     *
     *      配置外键（或者中间表）  @JoinColumn
     *          name：外键字段的名称；
     *          referencedColumnName：主表的主键名称
     * 由于配置了外键，所以一的一方将维护外键。这是不符合实际的。通常，外键由多的一方来维护；一的一方放弃维护外键
     */
//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
//    private Set<LinkMan> manSet = new HashSet<>();


    /**
     * 一对多 方法二：放弃维护外键 @OneToMany
     *      mappedBy：对方配置关系的属性名称
     *      cascade：级联操作。操作一个对象的同时，操作其关联的对象
     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<LinkMan> manSet = new HashSet<>();

    public Set<LinkMan> getManSet() {
        return manSet;
    }

    public void setManSet(Set<LinkMan> manSet) {
        this.manSet = manSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
}
