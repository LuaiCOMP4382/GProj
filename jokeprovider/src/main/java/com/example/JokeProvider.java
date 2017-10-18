package com.example;

import java.util.*;

public class JokeProvider {

    private static String[] jokes = new String[]{
            "I told my girlfriend she drew her eyebrows too high. She seemed surprised.",
            "My wife told me I had to stop acting like a flamingo. So I had to put my foot down.",
            "Two clowns are eating a cannibal. One turns to the other and says \"I think we got this joke wrong\"",
            "My friend says to me: \"What rhymes with orange\" I said: \"no it doesn't\"",
            "What's orange and sounds like a parrot?\n" +
                    "A carrot.",
            "A blind man walks into a bar. And a table. And a chair.",
            "This is my step ladder. I never knew my real ladder.",
            "I bought the world's worst thesaurus yesterday. Not only is it terrible, it's terrible."
    };

    public static String getRandomJoke() {

        Random random = new Random();

        return jokes[random.nextInt(jokes.length)];

    }
}