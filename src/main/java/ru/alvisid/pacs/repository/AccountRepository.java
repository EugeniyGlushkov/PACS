package ru.alvisid.pacs.repository;

import org.acs.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author GenCloud
 * @date 10/2018
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findAccountByUsername(String username);
}
