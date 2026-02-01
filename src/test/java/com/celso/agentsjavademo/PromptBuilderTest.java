package com.celso.agentsjavademo;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




/**
 * PromptBuilderTest
 *
 * Unit tests for the PromptBuilder component.
 *
 * These tests validate deterministic prompt construction
 * from structured messages, independent of any LLM
 * execution or HTTP interaction.
 */

class PromptBuilderTest {

    @Test
    void shouldBuildPromptWithMessages() {
        List<Message> messages = List.of(
            new Message("system", "You are a cook."),
            new Message("user", "What is cake?")
        );

        String prompt = PromptBuilder.build(messages);

        assertTrue(prompt.contains("System: You are a cook."));
        assertTrue(prompt.contains("User: What is cake?"));
        assertTrue(prompt.endsWith("Assistant:"));
    }
}

