
package com.celso.agentsjavademo;

import java.util.List;


/**
 * PromptBuilder
 *
 * Responsible for constructing a textual prompt from a sequence
 * of structured messages.
 *
 * This class isolates prompt formatting logic from transport,
 * execution, and model-specific concerns, allowing prompt
 * construction to be tested independently.
 */



public class PromptBuilder {

    public static String build(List<Message> messages) {
        StringBuilder sb = new StringBuilder();

        sb.append("""
        Answer the user's question directly and clearly.
        Do not repeat the question.
        Do not add unnecessary explanations.

        """);

        for (Message m : messages) {
            sb.append(cap(m.role()))
              .append(": ")
              .append(m.content())
              .append("\n");
        }

        sb.append("Assistant:");
        return sb.toString();
    }

    private static String cap(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}

