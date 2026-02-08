package com.example.catalogservice.domain.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "books")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String author;
    @Builder.Default
    Integer quantity = 0;
}
