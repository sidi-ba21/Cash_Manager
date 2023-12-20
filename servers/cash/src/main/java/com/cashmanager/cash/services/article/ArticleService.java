package com.cashmanager.cash.services.article;

import com.cashmanager.cash.models.Article;
import com.cashmanager.cash.payload.request.article.UpdateArticleRequest;
import com.cashmanager.cash.payload.request.article.AddArticleRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
class ArticleService implements IArticleService {

    @Autowired
    private final IArticleRepository articleRepository;

    @Override
    public Article add(AddArticleRequest data) {

        Article article = new Article(data.getName(), data.getPrice(), data.getQuantity());

//        log.info("Saving new article {}.", article);

        return this.articleRepository.save(article);
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
        if (data.getQuantity() != null) {
            article.setQuantity(data.getQuantity());
        }
        log.info("Updating article {}.", article);

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