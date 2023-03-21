package org.example.demo;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class DictionaryController extends AbstractSearchController {

    public DictionaryController() {
        super("english.txt");
    }

    @GetMapping("/dictionary")
    public List<String> getDictionaryList(@RequestParam(name = "search", defaultValue = "") String input) {
        return timedSearchWordList(input);
    }
}
