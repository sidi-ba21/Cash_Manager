package com.cashmanager.cash.services.clientaccount;

import com.cashmanager.cash.models.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface IClientAccountRepository extends JpaRepository<ClientAccount, Long> {

    // for check for login
    @Query("SELECT c FROM ClientAccount c WHERE c.email = :email AND c.password = :password")
    ClientAccount login(@Param("email") String email, @Param("password") String password);
}
