package com.celso.agentsjavademo;

import java.util.ArrayList;
import java.util.List;


/**
 * ProgrammaticPrompting
 *
 * This class demonstrates a **programmatic LLM interaction**
 * from a Java application using a locally hosted LLaMA-based model.
 *
 * The prompt is constructed explicitly in code and sent to a
 * local LLM server via HTTP. This is **not** an interactive chat
 * or web-based example.
 *
 * This example is inspired by concepts from the
 * "AI Agents in Java" course by Jules White, but the implementation
 * has been reorganized and adapted to work with a local LLM
 * (TinyLLaMA) instead of OpenAI APIs.
 */




public class ProgrammaticPrompting {

    
    
    public static void main(String[] args) {

        LLM llm = new LLM();

        // Create messages using the Message class
        List<Message> messages = new ArrayList<>();

        // Add system message
        messages.add(new Message("system",
                "You are a cook ready to help with everyday cooking questions."));
                
        String prompt = "What are the ingredients of a cake?";

        // Add user message
        messages.add(new Message("user", prompt));
        
        System.out.println("User: " + prompt);
        
        System.out.println();
        
        System.out.print("TinyLLaMA: ");

        // Generate response using the LLM class
        String response = llm.generateResponse(messages);
        System.out.println(response);
    }
}
