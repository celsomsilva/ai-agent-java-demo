package com.celso.agentsjavademo;

/**
 * Message
 *
 * Immutable value object representing a message exchanged
 * with a Large Language Model (LLM).
 *
 * This abstraction is intentionally minimal and provider-agnostic,
 * allowing the application to remain decoupled from any specific
 * LLM API or SDK.
 *
 * Supported roles typically include: "system", "user", and "assistant".
 */
public record Message(String role, String content) {}

