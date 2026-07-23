package com.kimss;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Convenience wrapper around {@code POST /v1/agents/run} response {@code res}.
 */
public final class AgentRunResult {
  private final JsonNode node;

  private AgentRunResult(JsonNode node) {
    this.node = node == null ? com.fasterxml.jackson.databind.node.NullNode.getInstance() : node;
  }

  static AgentRunResult from(JsonNode node) {
    return new AgentRunResult(node);
  }

  public JsonNode raw() {
    return node;
  }

  /** Best-effort assistant text from common response shapes. */
  public String text() {
    if (node == null || node.isNull()) {
      return "";
    }
    if (node.isTextual()) {
      return node.asText();
    }
    for (String key : new String[] {"text", "message", "content", "output", "answer"}) {
      JsonNode v = node.get(key);
      if (v != null && v.isTextual() && !v.asText().isBlank()) {
        return v.asText();
      }
    }
    JsonNode messages = node.get("messages");
    if (messages != null && messages.isArray() && messages.size() > 0) {
      JsonNode last = messages.get(messages.size() - 1);
      JsonNode content = last.get("content");
      if (content != null && content.isTextual()) {
        return content.asText();
      }
    }
    return node.toString();
  }

  public String conversationId() {
    for (String key : new String[] {"conversation_id", "thread_id", "conversationId", "threadId"}) {
      JsonNode v = node.get(key);
      if (v != null && v.isTextual() && !v.asText().isBlank()) {
        return v.asText();
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return text();
  }
}
