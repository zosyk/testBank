package com.west.bank.repository;

import com.west.bank.entity.CreditCard;
import com.west.bank.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {


}
