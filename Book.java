package com.digitallibrary.model;

import com.digitallibrary.exception.InvalidBookException;

public class Book {
    private final String id; // Keep `final` for immutability
    private String title;
    private String author;
    private String genre;
    private String availability;

    public Book(String id, String title, String author, String genre, String availability) 
            throws InvalidBookException {
        // Assign fields FIRST
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;

        // THEN validate (after initializing `final` fields)
        if (id == null || id.isEmpty()) 
            throw new InvalidBookException("Book ID cannot be empty.");
        if (title == null || title.isEmpty()) 
            throw new InvalidBookException("Title cannot be empty.");
        if (author == null || author.isEmpty()) 
            throw new InvalidBookException("Author cannot be empty.");
        if (!availability.equals("Available") && !availability.equals("Checked Out")) 
            throw new InvalidBookException("Invalid availability status.");
    }

    // Getters and setters (unchanged)
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getAvailability() { return availability; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailability(String availability) { this.availability = availability; }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", availability=" + availability + "]";
    }   
}