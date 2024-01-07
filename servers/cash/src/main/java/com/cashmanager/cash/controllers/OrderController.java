package com.cashmanager.cash.controllers;

import com.cashmanager.cash.models.Order;
import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.payload.response.order.OrderResponse;
import com.cashmanager.cash.payload.response.article.ArticleResponse;
import com.cashmanager.cash.services.order.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final IOrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> get(@RequestParam Long id) {
        log.info("get order by id : " + id);
        OrderResponse response = new OrderResponse();

        try {
            Order order = orderService.findById(id).orElse(null);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            responseBody(response, order);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/allorders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("get all orders");
        List<OrderResponse> responses = new ArrayList<>();

        try {
            List<Order> orders = orderService.findAllById(1L);
            if (orders == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responses);
            }

            for (Order order : orders) {
                OrderResponse response = new OrderResponse();
                responseBody(response, order);
                responses.add(response);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    private void responseBody(OrderResponse response, Order order) {
        response.setId(order.getId());
        response.setTotalPrice(order.getTotalPrice());
        response.setClientId(order.getClient().getId());
        List<Article> articles = order.getArticles();

        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article.getId());
            articleResponse.setName(article.getName());
            articleResponse.setPrice(article.getPrice());
            articleResponses.add(articleResponse);
        }
        response.setArticles(articleResponses);
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setTransactionType(order.getPayment().getType().toString());
        response.setIsPaid(order.getPayment().getAllowed());

    }
}
