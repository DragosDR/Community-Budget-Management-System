package com.example.Budget.Management.Application.repository;

import com.example.Budget.Management.Application.entity.adminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminAccountRepository extends JpaRepository<adminAccount,String> {
}
