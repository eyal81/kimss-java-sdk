# Kimss Java SDK — Claude Code

Read [docs/KIMSS_ONBOARDING.md](docs/KIMSS_ONBOARDING.md) first.

1. Official OpenAI Java client → `baseUrl("https://api.kimss.ai/v1")`, `apiKey("kimss_...")`.
2. Native `KimssClient` → `baseUrl("https://api.kimss.ai")`, `X-Kimss-Key`, `agents().run(id, msg)` (no `get()`).
3. Do not invent Anthropic or Azure inbound routes.
4. Kill switch: 403 `agent_disabled`. Never “zero-trust”.
