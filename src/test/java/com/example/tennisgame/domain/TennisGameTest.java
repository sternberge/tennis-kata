package com.example.tennisgame.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TennisGameTest {
    @Test
    void gameMustStartAtZero() {
        TennisGame game = new TennisGame();
        assertEquals("Player A : 0 / Player B : 0", game.getCurrentScore());
    }

    @Test
    void cannotGetWinnerBeforeGameOver() {
        TennisGame game = new TennisGame();
        assertThrows(IllegalStateException.class, game::getWinner);
    }

    @Test
    void cannotGetScoreAfterGameOver() {
        TennisGame game = new TennisGame();
        // A wins 4-0
        for (int i = 0; i < 4; i++) game.addPointToPlayerA();
        assertThrows(IllegalStateException.class, game::getCurrentScore);
    }
}
