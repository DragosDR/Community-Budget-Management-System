package com.example.Budget.Management.Application.repository;

import com.example.Budget.Management.Application.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<Admin,String> {
}