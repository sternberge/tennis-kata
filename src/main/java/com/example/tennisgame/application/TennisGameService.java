package com.example.tennisgame.application;

import com.example.tennisgame.domain.TennisGame;
import com.example.tennisgame.domain.exception.InvalidScoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TennisGameService {
    public List<String> computeGameScores(String score) {

        validateScore(score);

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

    private void validateScore(String sequence) {
        if (sequence == null) {
            throw new InvalidScoreException("Les scores ne peuvent pas être null.");
        }
        if (sequence.isEmpty()) {
            throw new InvalidScoreException("Les scores ne peuvent pas être vide.");
        }

        IntStream.range(0, sequence.length())
                .filter(i -> sequence.charAt(i) != 'A' && sequence.charAt(i) != 'B')
                .findFirst()
                .ifPresent(i -> {
                    throw new InvalidScoreException(sequence.charAt(i), i);
                });
    }
}
