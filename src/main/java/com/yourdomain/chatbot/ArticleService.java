package com.yourdomain.chatbot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private List<ArticleInfo> articles = new ArrayList<>();
    private List<ArticleInfo> articlesReadLater = new ArrayList<>();

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public NewsArticle addArticle(NewsArticle article) {
        return articleRepository.save(article);
    }

    public List<NewsArticle> getArticles() {
        return articleRepository.findAll();
    }

    public void addArticleInfo(ArticleInfo articleInfo) {
        articles.add(articleInfo);
    }

    public List<ArticleInfo> getArticlesInfo() {
        return articles;
    }
        
    public void addArticleReadLater(ArticleInfo articleInfo) {
        articlesReadLater.add(articleInfo);
    }

    public List<ArticleInfo> getArticlesReadLater() {
        return articlesReadLater;
    }

    public boolean removeArticleReadLater(Long id) {
        return articlesReadLater.removeIf(article -> article.getId().equals(id));
    }
}
