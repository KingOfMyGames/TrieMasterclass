package org.example.demo;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitController extends AbstractSearchController {

    public FruitController() {
        super("fruits.txt");
    }

    @GetMapping("/fruit")
    public List<String> getFruitList(@RequestParam(name = "search", defaultValue = "") String input) {
        return timedSearchWordList(input);
    }
}
