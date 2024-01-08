package com.cashmanager.cash.services.article;

import com.cashmanager.cash.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

interface IArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.cart.id = :id AND a.order = null")
    List<Article> getCartArticles(@Param("id") Long id);

    @Query("SELECT a FROM Article a WHERE a.cart = null AND a.order = null")
    List<Article> getArticlesWithoutCart();

    // get count of articles whithout cart, has same name and price return list of articles contains name and price and the count
//    @Query("SELECT a.name, a.price, COUNT(a) FROM Article a WHERE a.cart.id = null GROUP BY a.name, a.price")
//    List<Object[]> getArticlesWithoutCartGroupByNameAndPrice();
//
//    // get count of articles whith cart, has same name and price return list of articles contains name and price and the count
//    @Query("SELECT a.name, a.price, COUNT(a) FROM Article a WHERE a.cart.id = :id GROUP BY a.name, a.price")
//    List<Object[]> getArticlesWithCartGroupByNameAndPrice(@Param("id") Long id);

}