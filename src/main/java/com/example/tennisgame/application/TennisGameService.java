package com.example.tennisgame.application;

import com.example.tennisgame.domain.TennisGame;
import com.example.tennisgame.domain.exception.InvalidScoreException;

import java.util.ArrayList;
import java.util.List;

public class TennisGameService {
    public List<String> computeGameScores(String score) {

        validateSequence(score);

        List<String> outputs = new ArrayList<>();
        TennisGame game = new TennisGame();
        for (char c : score.toCharArray()) {
            if (c == 'A') {
                game.addPointToPlayerA();
            } else if (c == 'B') {
                game.addPointToPlayerB();
            }

            if (game.isGameOver()) {
                outputs.add("Player " + game.getWinner() + " wins the game");
                break;
            } else {
                outputs.add(game.getCurrentScore());
            }
        }
        return outputs;
    }

    private void validateSequence(String sequence) {
        if (sequence == null) {
            throw new InvalidScoreException("Les scores ne peuvent pas être null.");
        }
        if (sequence.isEmpty()) {
            throw new InvalidScoreException("Les scores ne peuvent pas être vide.");
        }
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (c != 'A' && c != 'B') {
                throw new InvalidScoreException(c, i);
            }
        }
    }
}
