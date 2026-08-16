# Kimss Java SDK — LLM / IDE context

Pair with [README.md](../README.md).

## Clean machine checklist

1. JDK **11+**.
2. Add Maven/Gradle dependency `ai.kimss:kimss-java` (or use JDK `HttpClient` + `X-Kimss-Key`).
3. Env: `KIMSS_API_KEY` (required). Optional: `KIMSS_BASE_URL` (default `https://api.kimss.ai`).
4. Preferred call: `KimssClient.fromEnv().agents().run(assistantId, "Hello!")` → `POST /v1/agents/run`.
5. Auth: **`X-Kimss-Key`** only for API keys — never `Authorization: Bearer` for Kimss keys.
6. Do not invent a Node `@kimss/sdk` package for JavaScript; use `fetch` + `X-Kimss-Key`.

## Request body for agents.run

```json
{
  "assistant_id": "asst_...",
  "usr_chat": "Hello!",
  "chat_type": "user_chat",
  "stream": false
}
```

Optional follow-up: `"thread_id": "<conversation_id>"`.

## Errors

| HTTP | `detail.error` | Behavior |
|------|----------------|----------|
| 403 | `subscription_required` | Stop; upgrade / switch workspace |
| 429 | `credit_*` / trial exhausted | Stop; surface to user |
| 429 | `rate_limit_exceeded` | Backoff / Retry-After |

Thrown as `KimssException` with `httpStatus()` and `errorCode()`.
