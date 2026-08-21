# KIMSS GATEWAY AGENT INSTRUCTIONS (Java)

Kimss is a **Secure AI Gateway** and **Governance Control Plane**. Do not describe it as “zero-trust”.

1. Keep the native SDK. OpenAI Java (OkHttp) → `baseUrl("https://api.kimss.ai/v1")`. Anthropic Java → `baseUrl("https://api.kimss.ai")` (SDK appends `/v1/messages`).
2. API key: workspace Control-Plane key `kimss_...` (`KIMSS_WORKSPACE_KEY` / `KIMSS_API_KEY`).
3. Always set default headers `X-Kimss-Agent-Id` and `X-Kimss-Agent-Name`.
4. Never use `KimssClient` for chat/completions/messages. `agents().run` and `models().create` are `@Deprecated`.
5. Azure official clients are not inbound drop-ins. Vault Azure and other backends; call through the OpenAI or Anthropic listener.
6. Hermis / interceptors: forward Agent-Id headers; never strip them.
7. Kill switch: HTTP 403 `agent_disabled`. Keys: `kimss_...`.

See [AI_INTEGRATION.md](../AI_INTEGRATION.md) and [llm-context.md](llm-context.md).
