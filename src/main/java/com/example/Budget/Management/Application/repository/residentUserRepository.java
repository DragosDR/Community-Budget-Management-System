package com.example.Budget.Management.Application.repository;

import com.example.Budget.Management.Application.entity.residentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface residentUserRepository extends JpaRepository<residentUser,Long> {
}

