package com.example.tennisgame.domain.exception;

public class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(char invalidChar, int position) {
        super(String.format("Caractère invalide '%c' à la position %d. Seuls 'A' et 'B' sont autorisés.", invalidChar, position + 1));
    }

    public InvalidScoreException(String message) {
        super(message);
    }
}
