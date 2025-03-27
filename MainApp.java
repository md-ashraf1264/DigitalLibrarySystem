package com.digitallibrary.app;

import com.digitallibrary.service.LibraryManager;
import com.digitallibrary.model.Book;
import com.digitallibrary.exception.InvalidBookException;
import java.util.Scanner;
import java.util.Optional;

public class MainApp {
    private static final LibraryManager manager = new LibraryManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readIntInput("Choose an option: ");
            handleUserChoice(choice);
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Digital Library System ---");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book by ID");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Exit");
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> addBookHandler();
            case 2 -> viewBooksHandler();
            case 3 -> searchBookHandler();
            case 4 -> updateBookHandler();
            case 5 -> deleteBookHandler();
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid option!");
        }
    }

    // Input Helper Methods
    private static int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume leftover newline
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Handlers
    private static void addBookHandler() {
        try {
            String id = readStringInput("Enter Book ID: ");
            String title = readStringInput("Title: ");
            String author = readStringInput("Author: ");
            String genre = readStringInput("Genre: ");
            String status = readStringInput("Status (Available/Checked Out): ");

            Book book = new Book(id, title, author, genre, status);
            manager.addBook(book);
            System.out.println("Book added successfully!");
        } catch (InvalidBookException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewBooksHandler() {
        System.out.println("\n--- All Books ---");
        manager.getAllBooks().forEach(System.out::println);
    }

    private static void searchBookHandler() {
        String id = readStringInput("Enter Book ID to search: ");
        Optional<Book> book = manager.searchBookById(id);
        if (book.isPresent()) {
            System.out.println("\nBook found: " + book.get());
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void updateBookHandler() {
        String id = readStringInput("Enter Book ID to update: ");
        String newTitle = readStringInput("New Title (leave empty to skip): ");
        String newAuthor = readStringInput("New Author (leave empty to skip): ");
        String newGenre = readStringInput("New Genre (leave empty to skip): ");
        String newStatus = readStringInput("New Status (Available/Checked Out, leave empty to skip): ");

        boolean success = manager.updateBook(id, newTitle, newAuthor, newGenre, newStatus);
        if (success) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Update failed. Book ID not found.");
        }
    }

    private static void deleteBookHandler() {
        String id = readStringInput("Enter Book ID to delete: ");
        boolean success = manager.deleteBook(id);
        if (success) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Delete failed. Book ID not found.");
        }
    }
}