# Getting started with the Kimss Secure AI Gateway (Java)

Route existing OpenAI-compatible traffic through Kimss in about five minutes. Kimss is the **Secure AI Gateway** and **Governance Control Plane**: identity, audit, kill switch, and a provider vault. You bring the models (**BYOI**).

**Developer tier (Always Free):** 25,000 governed requests/month, 14-day telemetry retention, 5 builder & admin seats. No credit card.

## Step 1 — Vault provider keys

Open **Governance → Provider Vault** (`/app/governance/custom-models`). Add your OpenAI, Azure OpenAI / Foundry, Anthropic, DeepSeek, or vLLM endpoint.

## Step 2 — Generate a Gateway key

**Gateway → Generate Key**. Copy the `kimss_...` secret once.

## Step 3 — Route traffic

### Zero-code `.env` (OpenAI Java client)

```bash
OPENAI_BASE_URL="https://api.kimss.ai/v1"
OPENAI_API_KEY="kimss_your_kimss_key"
```

### Official OpenAI Java client

```java
OpenAIClient client = OpenAIOkHttpClient.builder()
    .baseUrl("https://api.kimss.ai/v1")
    .apiKey("kimss_your_kimss_key")
    .build();
```

### Native Kimss Java SDK

```java
KimssClient client = KimssClient.builder()
    .apiKey(System.getenv("KIMSS_API_KEY"))
    .baseUrl("https://api.kimss.ai")
    .build();
AgentRunResult result = client.agents().run("asst_...", "Hello from Java!");
System.out.println(result.text());
```

Native Java uses header **`X-Kimss-Key`** (not Bearer). There is no `agents().get()` — call `agents().run(id, message)` directly.

Official Anthropic and Azure OpenAI inbound adapters are not available. Vault those providers and call Kimss `/v1` with an OpenAI-compatible client.

## Step 4 — Monitor and kill switch

Disable the agent under **Governance → Agents**. Routed calls return HTTP **403** with code **`agent_disabled`**.

## Related

- [README.md](README.md)
- [docs/KIMSS_ONBOARDING.md](docs/KIMSS_ONBOARDING.md)
- [docs/llm-context.md](docs/llm-context.md)
