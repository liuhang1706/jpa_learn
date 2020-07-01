package com.liuhang.springdatajpa.domain;

import javax.persistence.*;

/**
 * CREATE TABLE `cst_linkman`(
 * 	`lkm_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号（主键）',
 * 	`lkm_name` VARCHAR(16) DEFAULT NULL COMMENT '联系人姓名',
 * 	`lkm_cust_id` BIGINT(32) DEFAULT NULL COMMENT '客户ID',
 * 	`lkm_gender` CHAR(1) DEFAULT NULL COMMENT '联系人性别',
 * 	`lkm_phone` VARCHAR(16) DEFAULT NULL COMMENT '联系人办公电话',
 * 	`lkm_mobile` VARCHAR(16) DEFAULT NULL COMMENT '联系人手机',
 * 	`lkm_email` VARCHAR(64) DEFAULT NULL COMMENT '联系人邮箱',
 * 	`lkm_position` VARCHAR(16) DEFAULT NULL COMMENT '联系人职位',
 * 	`lkm_memo` VARCHAR(512) DEFAULT NULL COMMENT '联系人备注',
 * 	PRIMARY KEY (`lkm_id`),
 * 	KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
 * 	CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
 * ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 */
@Entity
@Table(name = "cst_linkman")
public class LinkMan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId;
    @Column(name = "lkm_name")
    private String lkmName;
    @Column(name = "lkm_gender")
    private String lkmGender;
    @Column(name = "lkm_phone")
    private String lkmPhone;
    @Column(name = "lkm_mobile")
    private String lkmMobile;
    @Column(name = "lkm_email")
    private String lkmEmail;
    @Column(name = "lkm_position")
    private String lkmPosition;
    @Column(name = "lkm_memo")
    private String lkmMemo;

    /**
     * 多对一配置：
     *          @ManyToOne
     *      配置外键（或者中间表）  @JoinColumn
     *          name：外键字段的名称；
     *          referencedColumnName：主表的主键名称
     * 配置了外键，所以多的一方将维护外键
     */
    // 配置linkMan与customer的多对一关系
    @ManyToOne(targetEntity = Customer.class)
    // 配置外键（或者中间表）
    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    /**
     * 设置toString方法需要注意
     * @return
     */
    @Override
    public String toString() {
        return "LinkMan{" +
                "lkmId=" + lkmId +
                ", lkmName='" + lkmName + '\'' +
                ", lkmGender='" + lkmGender + '\'' +
                ", lkmPhone='" + lkmPhone + '\'' +
                ", lkmMobile='" + lkmMobile + '\'' +
                ", lkmEmail='" + lkmEmail + '\'' +
                ", lkmPosition='" + lkmPosition + '\'' +
                ", lkmMemo='" + lkmMemo + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "LinkMan{" +
//                "lkmId=" + lkmId +
//                ", lkmName='" + lkmName + '\'' +
//                ", lkmGender='" + lkmGender + '\'' +
//                ", lkmPhone='" + lkmPhone + '\'' +
//                ", lkmMobile='" + lkmMobile + '\'' +
//                ", lkmEmail='" + lkmEmail + '\'' +
//                ", lkmPosition='" + lkmPosition + '\'' +
//                ", lkmMemo='" + lkmMemo + '\'' +
//                ", customer=" + customer +
//                '}';
//    }
}
