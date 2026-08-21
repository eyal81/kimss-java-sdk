# AI Integration — Kimss Gateway (Agent-to-Agent)

**Strict directive for Cursor, Windsurf, Claude Code, Devin, and other coding assistants.**

Kimss is a **Model-Agnostic Enterprise AI Gateway and Governance Control Plane**. Integrate via an invisible OpenAI-compatible proxy — never via custom chat wrappers.

## Rules (do not violate)

1. **Never** use a custom `KimssClient` for chat/completions or any LLM inference.
2. **Always** use the standard **OpenAI** native SDK for governed inference. Anthropic is a **vaulted provider** only — do **not** point Anthropic Messages clients at `api.kimss.ai`.
3. **Always** route traffic by setting the base URL to `https://api.kimss.ai/v1`.
4. **Always** inject `X-Kimss-Agent-Id` (and preferably `X-Kimss-Agent-Name`) for JIT attribution, audit, and kill-switch.
5. If using **Hermis** orchestration, pass those headers through the standard HTTP interceptor / default-headers layer — never strip them.

## Canonical Java pattern

```java
OpenAIClient client = OpenAIOkHttpClient.builder()
    .baseUrl("https://api.kimss.ai/v1")
    .apiKey(System.getenv("KIMSS_WORKSPACE_KEY"))
    .putHeader("X-Kimss-Agent-Id", System.getenv().getOrDefault("KIMSS_AGENT_ID", "enterprise_db_auditor"))
    .putHeader("X-Kimss-Agent-Name", System.getenv().getOrDefault("KIMSS_AGENT_NAME", "Database Auditor Agent"))
    .build();
```

## What `KimssClient` is for

Legacy / control-plane residual only. `agents().run(...)` and `models().create(...)` are **@Deprecated**. Prefer OpenAI OkHttp for all inference.

## Kill switch

HTTP **403** with `agent_disabled`. Keys are `kimss_...`. Never say “zero-trust”.
