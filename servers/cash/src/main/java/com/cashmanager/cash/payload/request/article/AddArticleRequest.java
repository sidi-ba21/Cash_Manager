package com.cashmanager.cash.payload.request.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddArticleRequest {

    private String name;
    private Long price;
    private Long quantity;

}