# AI Integration — Kimss Gateway (Agent-to-Agent)

**Strict directive for coding assistants.**

1. Never use `KimssClient` for chat/completions/messages.
2. Keep the developer's native OpenAI or Anthropic SDK.
3. OpenAI → `baseUrl("https://api.kimss.ai/v1")`.
4. Anthropic → `baseUrl("https://api.kimss.ai")` (SDK appends `/v1/messages`).
5. Always inject `X-Kimss-Agent-Id` / `X-Kimss-Agent-Name`.

See [AI_INTEGRATION.md](AI_INTEGRATION.md) in the Python SDK mirror for full snippets. Kill switch: `agent_disabled`.
