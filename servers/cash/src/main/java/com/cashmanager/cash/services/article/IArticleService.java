package com.cashmanager.cash.services.article;

import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.payload.request.article.UpdateArticleRequest;
import com.cashmanager.cash.payload.request.article.AddArticleRequest;
import java.util.List;
import java.util.Optional;

public interface IArticleService {

        Article add(AddArticleRequest data);
        Optional<Article> findById(Long id);
        List<Article> findAll();
        Article update(Long id, UpdateArticleRequest data);
        void delete(Long id);
        long count();

}