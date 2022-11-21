package org.elsys.ip.guessrest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("game")
public class GuessController {

    public Map<String, Long> games = new HashMap<>();
    Random random = new Random();

    @PostMapping
    public String startGame(
            @RequestParam(defaultValue = "${min.number}") long min,
            @RequestParam(defaultValue = "${max.number}") long max) {

        long secretNumber = min + (Math.abs(random.nextLong()) % (max-min+1));
        System.out.println("New game with number: " + secretNumber + " from range(" + min + " : " + max + ")");

        String gameId = UUID.randomUUID().toString();
        games.put(gameId, secretNumber);

        return gameId;
    }

    @GetMapping(path = "/{gameId}")
    public ResponseEntity<String> guess(@PathVariable String gameId, @RequestParam long guess) {
        if (!games.containsKey(gameId)) {
            return ResponseEntity.notFound().build();
        }
        long secretNumber = games.get(gameId);

        if (guess == secretNumber) {
            return ResponseEntity.status(200).build();
        } else if (guess > secretNumber) {
            return ResponseEntity.status(480).build();
        } else {
            return ResponseEntity.status(490).build();
        }
    }
}
