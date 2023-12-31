package com.cashmanager.cash.payload.request.cart;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCartRequest {
    private String typePayment;
    private Boolean allowed;
}
