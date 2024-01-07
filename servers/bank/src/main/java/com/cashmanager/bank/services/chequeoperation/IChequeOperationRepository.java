package com.cashmanager.bank.services.chequeoperation;

import com.cashmanager.bank.models.ChequeOperation;
import org.springframework.data.jpa.repository.JpaRepository;

interface IChequeOperationRepository extends JpaRepository<ChequeOperation, Long> {
}
