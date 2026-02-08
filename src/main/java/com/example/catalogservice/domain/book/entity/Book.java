package com.example.catalogservice.domain.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "books")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    @Builder.Default
    private Integer quantity = 0;
}
