package com.yourdomain.chatbot;

// DTO for article ID and title
public class ArticleInfo {
    private Long id;
    private String title;

    public ArticleInfo(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}