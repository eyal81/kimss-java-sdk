package com.kimss;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

/**
 * Model completions API ({@code POST /v1/models/completions}).
 *
 * @deprecated Prefer OpenAI {@code chat.completions} via {@code https://api.kimss.ai/v1}.
 */
public final class ModelsApi {
  private final KimssClient client;

  ModelsApi(KimssClient client) {
    this.client = client;
  }

  /**
   * @deprecated Use OpenAI OkHttp gateway proxy instead.
   */
  @Deprecated
  public JsonNode create(String modelId, List<Message> messages) {
    String mid = modelId == null ? "" : modelId.trim();
    if (mid.isEmpty()) {
      throw new IllegalArgumentException("modelId is required");
    }
    if (messages == null || messages.isEmpty()) {
      throw new IllegalArgumentException("messages is required");
    }
    ObjectNode body = client.mapper().createObjectNode();
    body.put("model", mid);
    body.put("stream", false);
    ArrayNode arr = body.putArray("messages");
    for (Message m : messages) {
      ObjectNode n = arr.addObject();
      n.put("role", m.getRole());
      n.put("content", m.getContent());
    }
    return client.postJson("/v1/models/completions", body);
  }

  public static final class Message {
    private final String role;
    private final String content;

    public Message(String role, String content) {
      this.role = role;
      this.content = content;
    }

    public static Message user(String content) {
      return new Message("user", content);
    }

    public String getRole() {
      return role;
    }

    public String getContent() {
      return content;
    }
  }
}
