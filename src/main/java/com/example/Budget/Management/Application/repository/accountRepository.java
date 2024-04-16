package com.example.Budget.Management.Application.repository;

import com.example.Budget.Management.Application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountRepository extends JpaRepository<Account,String> {
}