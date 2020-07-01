package com.liuhang.springdatajpa.dao;

import com.liuhang.springdatajpa.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}
