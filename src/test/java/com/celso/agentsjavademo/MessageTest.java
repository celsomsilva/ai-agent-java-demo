package com.celso.agentsjavademo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * MessageTest
 *
 * Unit tests for the Message value object.
 *
 * These tests verify basic construction and accessors,
 * ensuring the message model behaves as a simple,
 * immutable data carrier.
 */


class MessageTest {

    @Test
    void shouldCreateMessage() {
        Message m = new Message("user", "hello");

        assertEquals("user", m.role());
        assertEquals("hello", m.content());
    }
}

