package com.celso.agentsjavademo;



/**
 * Message
 *
 * Simple data structure representing a message exchanged
 * with a Large Language Model (LLM).
 *
 * This abstraction is intentionally minimal and provider-agnostic,
 * allowing the application to remain decoupled from any specific
 * LLM API or SDK.
 *
 * Supported roles typically include: "system", "user", and "assistant".
 */



public class Message {

    private String role;
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
