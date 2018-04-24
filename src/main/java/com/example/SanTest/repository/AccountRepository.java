package com.example.SanTest.repository;

import com.example.SanTest.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByName(String name1);
}
