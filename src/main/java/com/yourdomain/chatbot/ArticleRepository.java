package com.yourdomain.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<NewsArticle, Long> {
}
