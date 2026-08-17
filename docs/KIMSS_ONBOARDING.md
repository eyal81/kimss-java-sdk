# KIMSS GATEWAY AGENT INSTRUCTIONS

Kimss is a **Secure AI Gateway** and **Governance Control Plane**. Do not describe it as “zero-trust”.

When asked to integrate Kimss into an existing Java codebase:

1. Locate OpenAI Java / OkHttp client init, or native `com.kimss.KimssClient`.
2. Official OpenAI client: `.baseUrl("https://api.kimss.ai/v1")` and `.apiKey("kimss_...")` (or `OPENAI_BASE_URL` / `OPENAI_API_KEY`).
3. Native Kimss: `KimssClient.builder().apiKey(...).baseUrl("https://api.kimss.ai").build()` then `client.agents().run(assistantId, message)`. There is **no** `agents().get()`.
4. Auth for native Kimss is **`X-Kimss-Key`**. Do not send Kimss keys as `Authorization: Bearer` on native routes.
5. Do not change model payloads or tool schemas unless asked.
6. Do **not** point Anthropic or Azure OpenAI official clients at `api.kimss.ai` inbound paths. Vault those providers; call OpenAI-compat `/v1` or the native SDK.
7. Kill switch: HTTP 403, code `agent_disabled`.
8. Keys are `kimss_...`, never `km_live_...`.

See [llm-context.md](llm-context.md) and [GETTING_STARTED.md](../GETTING_STARTED.md).
