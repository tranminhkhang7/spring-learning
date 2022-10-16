package com.example.bookscorner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class BooksCornerController {
    @GetMapping
    public List<String> hello() {
        return List.of("Hello", "it's me!");
    }

}
