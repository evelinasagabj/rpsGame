package org.example.rpsgame;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public String evaluateWinner(
            @RequestParam("playerOneValue")
            String playerOneValue,
            @RequestParam("playerTwoValue")
            String playerTwoValue,
            Model model) {
        String result = gameService.evaluateWinner(playerOneValue, playerTwoValue);

        model.addAttribute("playerOneValue", playerOneValue);
        model.addAttribute("playerTwoValue", playerTwoValue);
        model.addAttribute("result", result);
        return "game";
    }

    @GetMapping("/game")
    public String game() {
        return "game";
    }
}
