package com.yourdomain.chatbot;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponse {
    private String status;
    private int totalResults;
    private NewsArticle[] articles;

    // getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public NewsArticle[] getArticles() {
        return articles;
    }

    public void setArticles(NewsArticle[] articles) {
        this.articles = articles;
    }
}

