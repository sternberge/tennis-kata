package com.example.tennisgame.application;

import com.example.tennisgame.domain.exception.InvalidScoreException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TennisGameServiceTest {

    private final TennisGameService service = new TennisGameService();

    @Test
    void exampleSequence() {
        List<String> expected = Arrays.asList(
                "Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A wins the game"
        );
        assertEquals(expected, service.computeGameScores("ABABAA"));
    }

    @Test
    void invalidCharacterThrowsException() {
        InvalidScoreException ex = assertThrows(
                InvalidScoreException.class,
                () -> service.computeGameScores("ABAX")
        );
        assertEquals("Caractère invalide 'X' à la position 4. Seuls 'A' et 'B' sont autorisés.", ex.getMessage());
    }

    @Test
    void nullSequenceThrowsException() {
        InvalidScoreException ex = assertThrows(
                InvalidScoreException.class,
                () -> service.computeGameScores(null)
        );
        assertEquals("Les scores ne peuvent pas être null.", ex.getMessage());
    }

    @Test
    void emptySequenceThrowsException() {
        InvalidScoreException ex = assertThrows(
                InvalidScoreException.class,
                () -> service.computeGameScores("")
        );
        assertEquals("Les scores ne peuvent pas être vide.", ex.getMessage());
    }

    @Test
    void sequenceWithLowercaseThrowsException() {
        InvalidScoreException ex = assertThrows(
                InvalidScoreException.class,
                () -> service.computeGameScores("ABAa")
        );
        assertEquals("Caractère invalide 'a' à la position 4. Seuls 'A' et 'B' sont autorisés.", ex.getMessage());
    }

    @Test
    void stopsProcessingAfterGameOver() {
        String sequence = "ABABAA" + "A".repeat(1000);

        List<String> result = service.computeGameScores(sequence);

        assertEquals(6, result.size()); // 5 scores + 1 victoire
        assertEquals("Player A wins the game", result.get(5));
    }
}
