package com.kimss;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Agent orchestration API ({@code POST /v1/agents/run}).
 */
public final class AgentsApi {
  private final KimssClient client;

  AgentsApi(KimssClient client) {
    this.client = client;
  }

  /**
   * Run one user turn against an agent (non-streaming).
   *
   * @param assistantId Kimss assistant / agent id
   * @param message     user text (sent as {@code usr_chat})
   */
  public AgentRunResult run(String assistantId, String message) {
    return run(assistantId, message, null, false);
  }

  public AgentRunResult run(String assistantId, String message, String conversationId, boolean stream) {
    if (stream) {
      throw new UnsupportedOperationException("Streaming is not implemented in kimss-java 0.1.2; use stream=false");
    }
    String aid = assistantId == null ? "" : assistantId.trim();
    String msg = message == null ? "" : message;
    if (aid.isEmpty()) {
      throw new IllegalArgumentException("assistantId is required");
    }
    if (msg.isBlank()) {
      throw new IllegalArgumentException("message is required");
    }
    ObjectNode body = client.mapper().createObjectNode();
    body.put("assistant_id", aid);
    body.put("usr_chat", msg);
    body.put("chat_type", "user_chat");
    body.put("stream", false);
    if (conversationId != null && !conversationId.isBlank()) {
      body.put("thread_id", conversationId.trim());
    }
    JsonNode root = client.postJson("/v1/agents/run", body);
    JsonNode res = root.has("res") ? root.get("res") : root;
    return AgentRunResult.from(res);
  }
}
