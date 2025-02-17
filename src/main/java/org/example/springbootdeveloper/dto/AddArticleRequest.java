package org.example.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddArticleRequest {
    private String title;
    private String content;
    private String email;

    //빌더 패턴을 사용해 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도
    public Article toEntity(String email){
        return Article.builder()
                .title(title)
                .content(content)
                .email(email)
                .build();
    }
}
