package com.example.tennisgame.domain;

public class TennisGame {
    private int scorePlayerA = 0;
    private int scorePlayerB = 0;

    public void addPointToPlayerA() {
        scorePlayerA++;
    }

    public void addPointToPlayerB() {
        scorePlayerB++;
    }

    public boolean isGameOver() {
        return (scorePlayerA >= 4 || scorePlayerB >= 4) && Math.abs(scorePlayerA - scorePlayerB) >= 2;
    }

    public String getWinner() {
        if (!isGameOver()) {
            throw new IllegalStateException("Game is not over yet.");
        }
        return scorePlayerA > scorePlayerB ? "A" : "B";
    }

    public String getCurrentScore() {
        if (isGameOver()) {
            throw new IllegalStateException("Game is over.");
        }
        String descA = getPointDescription(scorePlayerA, scorePlayerB);
        String descB = getPointDescription(scorePlayerB, scorePlayerA);
        return "Player A : " + descA + " / Player B : " + descB;
    }

    private String getPointDescription(int myScore, int opponentScore) {
        if (myScore < 3 || opponentScore < 3) {
            switch (myScore) {
                case 0: return "0";
                case 1: return "15";
                case 2: return "30";
                case 3: return "40";
                default:
                    throw new IllegalStateException("Unexpected score: " + myScore);
            }
        } else {
            if (myScore == opponentScore) {
                return "40";
            } else if (myScore > opponentScore) {
                return "AD";
            } else {
                return "40";
            }
        }
    }
}
