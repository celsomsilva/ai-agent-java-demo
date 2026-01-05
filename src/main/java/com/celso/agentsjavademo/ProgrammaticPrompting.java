package com.celso.agentsjavademo;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaticPrompting {

    //
    // Set your API key as shown in the GitHub repository
    // export OPENAI_API_KEY="your-api-key"
    //
    public static void main(String[] args) {

        LLM llm = new LLM();

        // Create messages using the Message class
        List<Message> messages = new ArrayList<>();

        // Add system message
        messages.add(new Message("system",
                "You are an expert software engineer that prefers functional programming."));
                
        String prompt = "Write a Java method that swaps the keys and values of a Map<K, V>. " +
        "Assume values are unique.";

        // Add user message
        messages.add(new Message("user", prompt));
        
        System.out.println("User: " + prompt);
        
        System.out.print("TinyLLaMA: ");

        // Generate response using the LLM class
        String response = llm.generateResponse(messages);
        System.out.println(response);
    }
}
