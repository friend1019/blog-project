package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;  // ✅ 올바른 import
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.ArticleViewResponse;
import org.example.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;
    //전체글 조회
    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleViewResponse> articles = blogService.findAll().stream()
                .map(ArticleViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);

        return "articleList";
    }
    //사용자 글 조회
    @GetMapping("/users/articles")
    public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String email = userDetails.getUsername();
        List<ArticleViewResponse> articles = blogService.findAllByEmail(email).stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("userArticles", articles);
        return "myPage";
    }

    //id로 글조회
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    //id와 이메일로 글조회
    @GetMapping("/users/articles/{id}")
    public String getArticleByEmailAndId(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails, Model model){
        String email = userDetails.getUsername();
        Article article = blogService.findById(id);
        model.addAttribute("userArticle", new ArticleViewResponse(article));
        return "userArticle";
    }

    //글 추가 창
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}