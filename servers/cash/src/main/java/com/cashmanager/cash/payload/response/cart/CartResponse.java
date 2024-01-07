package com.cashmanager.cash.payload.response.cart;

import com.cashmanager.cash.payload.response.article.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Long id;
    private Long clientId;
    private Long total;
    private List<ArticleResponse> articles;
}
