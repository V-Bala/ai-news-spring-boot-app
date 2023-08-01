package com.yourdomain.chatbot;

// DTO
public class ArticleInfo {
    private Long id;
    private String title;
    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}