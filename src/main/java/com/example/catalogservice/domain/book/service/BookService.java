package com.example.catalogservice.domain.book.service;

import com.example.catalogservice.domain.book.event.BookBorrowedEvent;
import com.example.catalogservice.domain.book.event.BookReturnedEvent;
import com.example.catalogservice.domain.book.exception.BookAlreadyExistsException;
import com.example.catalogservice.domain.book.exception.BookNotFoundException;
import com.example.catalogservice.domain.book.entity.Book;
import com.example.catalogservice.domain.book.repository.BookRepository;
import com.example.catalogservice.domain.book.repository.specification.BookPageSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Book createBook(String name, String author) {
        if (bookRepository.existsByName(name)) throw new BookAlreadyExistsException();
        Book book = Book.builder().name(name).author(author).build();
        return bookRepository.save(book);
    }

    @Transactional
    public Book restockBookById(Long bookId, Integer quantity) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.setQuantity(book.getQuantity() + quantity);
        return  bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Book> getBookPage(Integer page, Integer size, String name, String author) {
        Specification<Book> specification = new BookPageSpecification(name, author);
        PageRequest pageRequest = PageRequest.of(page, size);
        return bookRepository.findAll(specification, pageRequest).getContent();
    }

    @Transactional
    public void borrowBook(Long borrowingId, Long bookId) {
        boolean success = false;
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null && book.getQuantity() > 0) {
            success = true;
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
        }
        applicationEventPublisher.publishEvent(new BookBorrowedEvent(borrowingId, success));
    }

    @Transactional
    public void returnBook(Long borrowingId, Long bookId) {
        boolean success = false;
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            success = true;
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
        }
        applicationEventPublisher.publishEvent(new BookReturnedEvent(borrowingId, success));
    }

}