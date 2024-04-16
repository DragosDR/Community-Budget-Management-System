package com.example.Budget.Management.Application.repository;

import com.example.Budget.Management.Application.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface currencyRepository extends JpaRepository<Currency,String> {
}
