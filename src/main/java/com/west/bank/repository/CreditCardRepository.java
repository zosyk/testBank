package com.west.bank.repository;

import com.west.bank.entity.CreditCard;
import com.west.bank.utils.CustomPageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {


    @Query("select c from CreditCard c where c.number = :number")
    CreditCard findCardByNumber(@Param("number") long number);

    @Query("select c from CreditCard c where c.ownerID = :ownerID")
    List<CreditCard> findAllByUserID(@Param("ownerID") long ownerID);

    @Query("select c from CreditCard c where c.ownerID = :ownerID")
    Page<CreditCard> findAllByUserIDWithOffset(@Param("ownerID") long ownerID, Pageable Pageable);

    @Query("select max(c.number) from CreditCard c")
    long getMaxCardNumber();
}
