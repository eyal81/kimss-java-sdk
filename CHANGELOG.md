# Changelog

## [Unreleased]

### Fixed

- Onboarding and getting-started docs match dual-listener inbound: Anthropic Java client at `https://api.kimss.ai`. Azure official clients remain vault-only.

## [0.2.0] — 2026-08-21

### Deprecated

- `AgentsApi.run(...)` and `ModelsApi.create(...)` are `@Deprecated`. Prefer OpenAI OkHttp with `baseUrl("https://api.kimss.ai/v1")` and `X-Kimss-Agent-Id` headers ([AI_INTEGRATION.md](AI_INTEGRATION.md)).

### Changed

- Docs repositioned around the gateway proxy pattern; package is residual control-plane / legacy only.

## [0.1.2]

Initial Maven Central publish (`ai.kimss:kimss-java`).
