package com.cashmanager.cash.services.article;

import com.cashmanager.cash.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

interface IArticleRepository extends JpaRepository<Article, Long> {

}