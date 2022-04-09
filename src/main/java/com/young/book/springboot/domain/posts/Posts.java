package com.young.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter // 롬복
@NoArgsConstructor // 롬복 , 기본 생성자 자동추가
@Entity // 테이블과 링크될 클래스
public class Posts { // 실제 DB의 테이블과 매칭될 클래스 = Entity 클래스

    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙 (옵션 추가해야만 auto_increment 가능)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
