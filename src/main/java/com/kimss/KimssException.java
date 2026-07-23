package com.kimss;

/**
 * Kimss API error. Parse {@link #errorCode()} for subscription / credit / rate-limit handling.
 */
public final class KimssException extends RuntimeException {
  private final int httpStatus;
  private final String errorCode;
  private final String responseBody;

  public KimssException(int httpStatus, String errorCode, String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.responseBody = null;
  }

  public KimssException(int httpStatus, String errorCode, String message, Throwable cause) {
    super(message, cause);
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.responseBody = null;
  }

  private KimssException(int httpStatus, String errorCode, String message, String responseBody) {
    super(message);
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.responseBody = responseBody;
  }

  static KimssException fromHttp(int status, String body) {
    String code = "http_" + status;
    String message = body == null ? ("HTTP " + status) : body;
    try {
      com.fasterxml.jackson.databind.JsonNode root =
          new com.fasterxml.jackson.databind.ObjectMapper().readTree(body == null ? "{}" : body);
      com.fasterxml.jackson.databind.JsonNode detail = root.get("detail");
      if (detail != null && detail.isObject()) {
        com.fasterxml.jackson.databind.JsonNode err = detail.get("error");
        com.fasterxml.jackson.databind.JsonNode msg = detail.get("message");
        if (err != null && err.isTextual()) {
          code = err.asText();
        }
        if (msg != null && msg.isTextual()) {
          message = msg.asText();
        }
      }
    } catch (Exception ignored) {
      // keep raw body as message
    }
    return new KimssException(status, code, message, body);
  }

  public int httpStatus() {
    return httpStatus;
  }

  public String errorCode() {
    return errorCode;
  }

  public String responseBody() {
    return responseBody;
  }
}
