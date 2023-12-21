package com.cashmanager.cash.services.article;

import com.cashmanager.cash.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

interface IArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.cart.id = :id")
    List<Article> getCartArticles(@Param("id") Long id);

    @Query("SELECT a FROM Article a WHERE a.cart.id = null")
    List<Article> getArticlesWithoutCart();

}