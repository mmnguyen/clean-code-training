package com.exxeta.cleancode.demo;

import org.springframework.stereotype.Service;

@Service
public class GreetingsResource {
    public String greetings(String toGreet) {
        return String.format("Hello my friend %s", toGreet);
    }
}
