package com.cashmanager.cash.services.article;

import com.cashmanager.cash.models.*;
import com.cashmanager.cash.payload.request.article.UpdateArticleRequest;
import com.cashmanager.cash.payload.request.article.AddArticleRequest;
import com.cashmanager.cash.services.clientaccount.IClientAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
class ArticleService implements IArticleService {

    @Autowired
    private final IArticleRepository articleRepository;

    @Autowired
    private IClientAccountService clientAccountService;

    @Override
    public Article add(AddArticleRequest data) {

        Article article = new Article(data.getName(), data.getPrice());

//        log.info("Saving new article {}.", article);

        return this.articleRepository.save(article);
    }

    @Override
    public Article setInCart(Long id, Cart cart) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        article.setCart(cart);
        return articleRepository.save(article);
    }

    @Override
    public Article removeFromCart(Long id, Cart cart) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        article.setCart(null);
        return articleRepository.save(article);
    }

    @Override
    public Article setInOrder(Long id, Order order) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        article.setOrder(order);
        return articleRepository.save(article);
    }

    @Override
    public Article removeFromOrder(Long id, Order order) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        article.setOrder(null);
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getCartArticles(Long id) {
        ClientAccount clientAccount = clientAccountService.findById(id).orElse(null);
        if (clientAccount == null) {
            return null;
        }
        Client client = clientAccount.getClient();
        Long cartId = client.getCart().getId();

        List <Article> articles = articleRepository.getCartArticles(cartId);
        if (articles.isEmpty()) {
            return new ArrayList<>();
        }

        return articles;
    }

    @Override
    public List<Article> getArticlesWithoutCart() {
        List <Article> articles = articleRepository.getArticlesWithoutCart();

        if (articles.isEmpty()) {
            return new ArrayList<>();
        }
        return articles;
    }

    @Override
    public Article update(Long id, UpdateArticleRequest data) {
        Optional<Article> existingArticle = articleRepository.findById(id);


        if (existingArticle.isEmpty()) {
            return null;
        }

        Article article = existingArticle.get();

        if (data.getName() != null) {
            article.setName(data.getName());
        }
        if (data.getPrice() != null) {
            article.setPrice(data.getPrice());
        }
    //    log.info("Updating article {}.", article);

            return articleRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public long count() {
        return articleRepository.count();
    }
}