package com.yourdomain.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

@Service
public class NewsApiService {

    @Autowired
    private ArticleService articleService;

    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey=235403d0bfcc436aa65de11f9345903f";

    public List<ArticleInfo> getLatestTechNews() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(NEWS_API_URL, String.class);
        String body = responseEntity.getBody();

        NewsApiResponse newsApiResponse;
        try {
            newsApiResponse = objectMapper.readValue(body, NewsApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<NewsArticle> articles = Arrays.asList(newsApiResponse.getArticles());

        // Create ArticleInfo objects and save them in ArticleService
        Long id = 1L;
        for (NewsArticle article : articles) {
            String cleanTitle = article.getTitle().replaceAll("[^a-zA-Z0-9\\s]", "");
            ArticleInfo articleInfo = new ArticleInfo(id++, cleanTitle);
            articleService.addArticleInfo(articleInfo);
        }

        // Get the list of articles from ArticleService
        List<ArticleInfo> savedArticles = articleService.getArticlesInfo();
        return savedArticles;
    }

    public Optional<ArticleInfo> getArticleById(Long id) {
        List<ArticleInfo> articles = articleService.getArticlesInfo();
        return articles.stream().filter(article -> article.getId().equals(id)).findFirst();
    }
}
