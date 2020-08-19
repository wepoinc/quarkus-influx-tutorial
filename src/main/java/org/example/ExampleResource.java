package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/hello", produces = "text/plain")
public class ExampleResource {

    @GetMapping
    public String hello() {
        return "hello";
    }
}