package com.west.bank.repository;

import com.west.bank.entity.BankClient;
import com.west.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankClientRepository extends JpaRepository<BankClient, Long> {


    @Query("select b from BankClient b where b.username = :username")
    BankClient findBankClientByUsername(@Param("username") String username);

}
