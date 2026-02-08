package com.example.catalogservice.domain.book.repository.specification;

import com.example.catalogservice.domain.book.entity.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookPageSpecification implements Specification<Book> {

    private final String name;
    private final String author;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(cb.like(cb.lower(root.get("name")), toSearchPattern(name)));
        }
        if (author != null) {
            predicates.add(cb.like(cb.lower(root.get("author")), toSearchPattern(author)));
        }
        return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
    }

    private static String toSearchPattern(String string) {
        return "%" + string.trim().toLowerCase() + "%";
    }

}
