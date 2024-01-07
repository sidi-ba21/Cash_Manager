package com.cashmanager.cash.payload.response.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private Long id;
    private String name;
    private Long price;
    private Long cartId;
}