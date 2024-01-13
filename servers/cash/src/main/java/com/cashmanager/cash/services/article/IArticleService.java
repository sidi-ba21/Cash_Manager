package com.cashmanager.cash.services.article;

import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.models.Cart;
import com.cashmanager.cash.models.Order;
import com.cashmanager.cash.payload.request.article.UpdateArticleRequest;
import com.cashmanager.cash.payload.request.article.AddArticleRequest;
import java.util.List;
import java.util.Optional;

public interface IArticleService {

        Article add(AddArticleRequest data);
        Optional<Article> findById(Long id);
        List<Article> findAll();
        Article findByName(String name);

        Article setInCart(Long id, Cart cart);

        Article removeFromCart(Long id, Cart cart);

        Article setInOrder(Long id, Order order);

        Article removeFromOrder(Long id, Order order);


        List<Article> getCartArticles(Long id);

        List<Article> getArticlesWithoutCart();

        Article update(Long id, UpdateArticleRequest data);
        void delete(Long id);
        long count();

}
