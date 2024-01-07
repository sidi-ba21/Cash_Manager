package com.cashmanager.cash.payload.request.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleRequest {

    private String name;
    private Long price;
    private Long quantity;

}