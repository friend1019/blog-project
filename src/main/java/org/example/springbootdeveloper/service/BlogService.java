package org.example.springbootdeveloper.service;

import java.util.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.AddArticleRequest;
import org.example.springbootdeveloper.dto.UpdateArticleRequest;
import org.example.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

//final이 붙은 필드의 생성자 자동생성
@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request, String email) {
        Article article = request.toEntity(email);
        return blogRepository.save(article);
    }

    //findAll은 JPA 지원 메서드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    //특정 사용자가 쓴 글 리스트 업
    public List<Article> findAllByEmail(String email) {
        return blogRepository.findByEmail(email);
    }

    //JPA 제공메서드인 findById 활용해 id로 글 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public Article findByEmailAndId(String email, Long id) {
        return blogRepository.findByEmailAndId(email, id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    //글 삭제
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
