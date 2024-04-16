package com.example.Budget.Management.Application.repository;

import com.example.Budget.Management.Application.entity.defCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface defCurrencyRepository extends JpaRepository<defCurrency,String> {
}