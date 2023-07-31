package com.yourdomain.chatbot;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    private final NewsApiService newsApiService;
    private final ArticleService articleService;
    private final OpenAIService openAiService;

    public ApiController(NewsApiService newsApiService, ArticleService articleService, OpenAIService openAiService) {
        this.newsApiService = newsApiService;
        this.articleService = articleService;
        this.openAiService = openAiService;
    }

    @GetMapping("/tech-news")
    public List<ArticleInfo> getLatestTechNews() {
        return newsApiService.getLatestTechNews();
    }

    @PostMapping("/read-later/{id}")
    public ResponseEntity<Void> addArticleToReadLater(@PathVariable Long id) {
        Optional<ArticleInfo> articleOptional = newsApiService.getArticleById(id);
        if (articleOptional.isPresent()) {
            articleService.addArticleReadLater(articleOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/read-later")
    public List<ArticleInfo> getReadLaterArticles() {
        return articleService.getArticlesReadLater();
    }
    
    @GetMapping("/openai-request")
    public String sendToOpenAI() {
        List<ArticleInfo> articles = articleService.getArticlesReadLater();
        ArticleInfo sendIt = articles.iterator().next();
        System.out.println("ID: " + sendIt.getId() + ", Title: " + sendIt.getTitle());
        return openAiService.generateResponse(articles.iterator().next());
    }

}
