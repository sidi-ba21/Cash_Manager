package com.cashmanager.bank.controllers.api;

import com.cashmanager.bank.payload.request.client.CreateTransactionRequest;
import com.cashmanager.bank.payload.response.CreateTransactionResponse;
import com.cashmanager.bank.services.transaction.ITransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionController {

	private final ITransactionService transactionService;

	@PostMapping
	public ResponseEntity<CreateTransactionResponse> create(@RequestBody CreateTransactionRequest ctr) {
		CreateTransactionResponse response = new CreateTransactionResponse();

		try {

			log.info(ctr.toString());

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}


}
