package org.elsys.ip.guessrest;

import org.elsys.ip.guessrest.service.GuessGameService;
import org.elsys.ip.guessrest.service.GuessResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("game")
public class GuessController {

    private final GuessGameService guessGameService;

    public GuessController(GuessGameService guessGameService) {
        this.guessGameService = guessGameService;
    }


    @PostMapping
    public String startGame(
            @RequestParam(defaultValue = "${min.number}") long min,
            @RequestParam(defaultValue = "${max.number}") long max) {

        return guessGameService.startGame(min, max);
    }

    @GetMapping(path = "/{gameId}")
    public ResponseEntity<String> guess(@PathVariable String gameId, @RequestParam long guess) {
        GuessResult result = guessGameService.guess(gameId, guess);
        switch(result){

            case MATCH -> {
                return ResponseEntity.ok().build();
            }
            case GREATER -> {
                return ResponseEntity.status(490).build();
            }
            case LOWER -> {
                return ResponseEntity.status(480).build();
            }
            default  -> {
                return ResponseEntity.status(404).build();
            }
        }
    }


}
