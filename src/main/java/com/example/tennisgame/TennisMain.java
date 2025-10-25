package com.example.tennisgame;

import com.example.tennisgame.application.TennisGameService;

import java.util.List;

public class TennisMain {
    public static void main(String[] args) {
        String sequence = args.length > 0 ? args[0] : "ABABAA";
        TennisGameService service = new TennisGameService();
        List<String> scores = service.computeGameScores(sequence);
        for (String score : scores) {
            System.out.println(score);
        }
    }
}
