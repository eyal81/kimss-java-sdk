package com.kimss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KimssClientTest {
  @Test
  void builderRequiresApiKey() {
    assertThrows(IllegalArgumentException.class, () -> KimssClient.builder().apiKey("").build());
  }

  @Test
  void stripsTrailingSlash() {
    KimssClient c = KimssClient.builder().apiKey("test-key").baseUrl("https://api.kimss.ai/").build();
    assertEquals("https://api.kimss.ai", c.baseUrl());
  }

  @Test
  void agentRunResultTextPrefersTextField() throws Exception {
    com.fasterxml.jackson.databind.ObjectMapper m = new com.fasterxml.jackson.databind.ObjectMapper();
    AgentRunResult r = AgentRunResult.from(m.readTree("{\"text\":\"hello\",\"thread_id\":\"t1\"}"));
    assertEquals("hello", r.text());
    assertEquals("t1", r.conversationId());
  }
}
