package com.celso.agentsjavademo;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.List;

public class LLM {

/**
 * Generates a response from a Large Language Model based on a list of messages.
 *
 * This method acts as an abstraction layer between the application and the
 * underlying LLM provider. By isolating all HTTP, JSON, and prompt-handling
 * logic in a single place, the rest of the codebase remains independent from
 * any specific vendor or API format.
 *
 * The current implementation targets a local LLaMA-compatible endpoint
 * and performs a single request-response interaction per invocation.
 *
 * Benefits of this approach:
 * - Provider flexibility: the backend model or API can be replaced with minimal impact.
 * - API stability: changes in external LLM APIs are contained within this class.
 * - Testability: responses can be mocked without relying on third-party SDKs.
 * - Observability and control: logging, error handling, and execution limits are centralized.
 *
 * While the current implementation uses a locally hosted LLaMA server,
 * the design allows alternative providers or adapters to be introduced.
 *
 * @param messages list of user/system messages used to build the prompt
 * @return generated model response as plain text
 */


	
	
    /**  
     *
     * USING LLAMA INSTEAD OF OPENAI
     *	
     */
	

    private static final String LLAMA_ENDPOINT =
            "http://localhost:8080/v1/completions";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    
    

    /*
     * Public API (equivalent to the OpenAI SDK)
     */
     
    public String generateResponse(List<Message> messages) {

        String prompt = buildPrompt(messages);
        String requestBody = buildRequestBody(prompt);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LLAMA_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return parseResponse(response.body());

        } catch (Exception e) {
            throw new RuntimeException("Error while calling local TinyLLaMA", e);
        }
    }

    /*
     * Prompt construction	
     */
     
    private String buildPrompt(List<Message> messages) {
        StringBuilder sb = new StringBuilder();

        sb.append("""
        Answer the user's question directly and clearly.
        Do not repeat the question.
        Do not add unnecessary explanations.

        """);

        for (Message m : messages) {
            sb.append(cap(m.getRole()))
              .append(": ")
              .append(m.getContent())
              .append("\n");
        }

        sb.append("Assistant:");
        return sb.toString();
    }

    private static String cap(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    

    /*
     *  JSON request (proper escaping, only here)	
     */
    private static String buildRequestBody(String prompt) {
        return """
        {
          "model": "local",
          "prompt": %s,
          "max_tokens": 512,
          "temperature": 0.2,
          "stop": ["User:"]
        }
        """.formatted(jsonEscape(prompt));
    }

    private static String jsonEscape(String s) {
        return "\"" + s
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n") + "\"";
    }
    

    /*
     *  Response parsing (REPLACES the OpenAI SDK)	
     */
    private String parseResponse(String json) {
    try {
        JsonNode root = mapper.readTree(json);

        JsonNode choices = root.get("choices");
        if (choices == null || !choices.isArray() || choices.isEmpty()) {
            throw new IllegalStateException("Missing or empty 'choices' in LLaMA response");
        }

        JsonNode textNode = choices.get(0).get("text");
        if (textNode == null || textNode.isNull()) {
            throw new IllegalStateException("Missing 'text' field in LLaMA choice");
        }

        return textNode.asText().trim();

    } catch (Exception e) {
        throw new RuntimeException("Failed to parse LLaMA response", e);
    }
}

}
