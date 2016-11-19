package com.west.bank.repository;

import com.west.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("select t from Transaction t where t.fromID = :fromID or t.toID = :fromID")
    List<Transaction> findByCardId(@Param("fromID") long fromID);
}
