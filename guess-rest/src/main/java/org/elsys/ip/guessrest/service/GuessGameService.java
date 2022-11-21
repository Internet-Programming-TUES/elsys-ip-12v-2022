package org.elsys.ip.guessrest.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class GuessGameService {
    private final Map<String, Long> games = new HashMap<>();
    Random random = new Random();

    public Map<String, Long> getGames() {
        return games;
    }

    public String startGame(long min, long max) {
        long secretNumber = min + (Math.abs(random.nextLong()) % (max - min +1));
        System.out.println("New game with number: " + secretNumber + " from range(" + min + " : " + max + ")");

        String gameId = UUID.randomUUID().toString();
        games.put(gameId, secretNumber);
        return gameId;
    }

    public GuessResult guess(String gameId, long guess) {
        if (!games.containsKey(gameId)) {
            return GuessResult.NOT_FOUND;
        }
        long secretNumber = games.get(gameId);

        if (guess == secretNumber) {
            return GuessResult.MATCH;
        } else if (guess > secretNumber) {
            return GuessResult.LOWER;
        } else {
            return GuessResult.GREATER;
        }
    }
}
