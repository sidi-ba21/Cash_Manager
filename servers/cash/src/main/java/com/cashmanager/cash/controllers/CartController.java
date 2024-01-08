package com.cashmanager.cash.controllers;

import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.models.Cart;
import com.cashmanager.cash.payload.request.cart.*;
import com.cashmanager.cash.payload.response.article.ArticleResponse;
import com.cashmanager.cash.payload.response.cart.*;
import com.cashmanager.cash.services.cart.ICartService;
import com.cashmanager.cash.services.article.IArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/carts")
@AllArgsConstructor
@Slf4j
public class CartController {

    private final ICartService cartService;
    private final IArticleService articleService;

    @PostMapping("/add")
    public ResponseEntity<CartResponse> add(@RequestBody AddItemCartRequest data) {
        CartResponse response = new CartResponse();

        try {
            Cart cart = cartService.add(1L, data);
            responseBody(response, cart);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> get(@RequestParam Long id) {
        log.info("get cart by client id : " + id);
        CartResponse response = new CartResponse();

        try {
            Cart cart = cartService.findById(id).orElse(null);
            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            responseBody(response, cart);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/remove")
    public ResponseEntity<CartResponse> remove(@RequestBody DeleteItemCartRequest data) {
        CartResponse response = new CartResponse();

        try {
            Cart cart = cartService.findById(1L).orElse(null);
            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            cartService.delete(1L, data);
            responseBody(response, cart);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<CartResponse> validate(@RequestBody ValidateCartRequest data) {
        CartResponse response = new CartResponse();

        try {
            Cart cart = cartService.findById(1L).orElse(null);
            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            cartService.validateCart(1L, data);
            responseBody(response, cart);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    private void responseBody(CartResponse response, Cart cart) {
        Long total = 0L;

        // get all articles from cart and add them to response and calculate total price
        List<Article> articles = articleService.getCartArticles(cart.getClient().getId());
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article.getId());
            articleResponse.setName(article.getName());
            articleResponse.setPrice(article.getPrice());
            articleResponses.add(articleResponse);
            total += article.getPrice();
        }
        response.setId(cart.getId());
        response.setClientId(cart.getClient().getId());
        response.setArticles(articleResponses);
        response.setTotal(total);
    }
}
