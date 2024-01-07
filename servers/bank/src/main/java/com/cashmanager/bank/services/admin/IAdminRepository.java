package com.cashmanager.bank.services.admin;

import com.cashmanager.bank.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface IAdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.login = :login")
    Optional<Admin> findByLogin(@Param("login") String login);

}
