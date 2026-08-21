# Kimss Java SDK — Claude Code

Read [AI_INTEGRATION.md](AI_INTEGRATION.md) first.

1. Never use `KimssClient` for chat/completions.
2. Official OpenAI Java client → `baseUrl("https://api.kimss.ai/v1")`, `apiKey("kimss_...")`, plus `X-Kimss-Agent-Id` / `X-Kimss-Agent-Name` headers.
3. Do not invent Anthropic or Azure inbound routes.
4. Hermis: pass Agent-Id headers through the HTTP interceptor layer.
5. Kill switch: 403 `agent_disabled`. Never “zero-trust”.

Deprecated: `KimssClient.agents().run` / `models().create` — do not generate new call sites.
