package com.cashmanager.cash.controllers;

import com.cashmanager.cash.payload.request.article.*;
import com.cashmanager.cash.payload.response.article.*;
import com.cashmanager.cash.services.article.IArticleService;
import com.cashmanager.cash.models.Article;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/articles")
@AllArgsConstructor
@Slf4j
public class ArticleController {
    private final IArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponse> add(@RequestBody AddArticleRequest data) {
        ArticleResponse response = new ArticleResponse();

        try {
            Article article = articleService.add(data);
            responseBody(response, article);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> get(@RequestParam Long id) {
        log.info("get article by id : " + id);
        ArticleResponse response = new ArticleResponse();

        try {
            Article article = articleService.findById(id).orElse(null);
            if (article == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            responseBody(response, article);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/available")
    public ResponseEntity<List<ArticleResponse>> getAvailable() {
        log.info("get available articles");
        List<ArticleResponse> response = new ArrayList<>();

        try {
            List<Article> articles = articleService.getArticlesWithoutCart();
            for (Article article : articles) {
                ArticleResponse articleResponse = new ArticleResponse();
                responseBody(articleResponse, article);
                response.add(articleResponse);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAll() {
        log.info("get all articles");
        List<ArticleResponse> response = new ArrayList<>();

        try {
            List<Article> articles = articleService.findAll();
            for (Article article : articles) {
                ArticleResponse articleResponse = new ArticleResponse();
                responseBody(articleResponse, article);
                response.add(articleResponse);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> update(@RequestParam Long id, @RequestBody UpdateArticleRequest data) {
        ArticleResponse response = new ArticleResponse();

        try {
            Article article = articleService.update(id, data);
            if (article == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            responseBody(response, article);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleResponse> delete(@RequestParam Long id) {
        ArticleResponse response = new ArticleResponse();

        try {
            articleService.delete(id);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void responseBody(ArticleResponse response, Article article) {
        response.setId(article.getId());
        response.setName(article.getName());
        response.setPrice(article.getPrice());
        response.setCartId(article.getCart() != null ? article.getCart().getId() : null);
    }
}