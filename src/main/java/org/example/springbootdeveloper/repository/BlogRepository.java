package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Article, Long> {
    List<Article> findByEmail(String email);
    Optional<Article> findByEmailAndId(String email, Long id);
    void deleteById(Long id);
}