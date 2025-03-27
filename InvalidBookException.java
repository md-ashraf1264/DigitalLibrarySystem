package com.digitallibrary.exception;

public class InvalidBookException extends Exception {
    public InvalidBookException(String message) {
        super(message); // This line was likely missing!
    }
}