package com.digitallibrary.service;

import com.digitallibrary.model.Book;
import com.digitallibrary.exception.InvalidBookException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryManager {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) throws InvalidBookException {
        if (isBookIdExists(book.getId())) {
            throw new InvalidBookException("Book ID already exists.");
        }
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Optional<Book> searchBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public boolean updateBook(String id, String newTitle, String newAuthor, String newGenre, String newStatus) {
        Optional<Book> optionalBook = searchBookById(id);
        if (optionalBook.isEmpty()) return false;

        Book book = optionalBook.get();
        if (!newTitle.isEmpty()) book.setTitle(newTitle);
        if (!newAuthor.isEmpty()) book.setAuthor(newAuthor);
        if (!newGenre.isEmpty()) book.setGenre(newGenre);
        if (newStatus.equals("Available") || newStatus.equals("Checked Out")) {
            book.setAvailability(newStatus);
        }
        return true;
    }

    public boolean deleteBook(String id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    private boolean isBookIdExists(String id) {
        return books.stream().anyMatch(book -> book.getId().equals(id));
    }
}