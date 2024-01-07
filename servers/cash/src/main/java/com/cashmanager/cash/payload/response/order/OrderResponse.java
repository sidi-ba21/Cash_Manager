package com.cashmanager.cash.payload.response.order;

import com.cashmanager.cash.models.Payment;
import com.cashmanager.cash.payload.response.article.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

        private Long id;
        private Long clientId;
        private Long totalPrice;
        private List<ArticleResponse> articles;
        private Payment payment;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String transactionType;
        private Boolean isPaid;
}
