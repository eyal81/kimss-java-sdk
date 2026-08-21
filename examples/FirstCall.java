import com.kimss.AgentRunResult;
import com.kimss.KimssClient;

/**
 * DEPRECATED — prefer {@link GatewayProxyFirstCall} (OpenAI OkHttp + Agent-Id headers).
 *
 * Requires:
 *   export KIMSS_API_KEY=...
 *   export KIMSS_ASSISTANT_ID=asst_...
 */
@Deprecated
public class FirstCall {
  public static void main(String[] args) {
    String assistantId = System.getenv("KIMSS_ASSISTANT_ID");
    if (assistantId == null || assistantId.isBlank()) {
      System.err.println("Set KIMSS_ASSISTANT_ID (or use GatewayProxyFirstCall)");
      System.exit(1);
    }
    KimssClient client = KimssClient.fromEnv();
    @SuppressWarnings("deprecation")
    AgentRunResult result = client.agents().run(assistantId, "Hello from kimss-java!");
    System.out.println(result.text());
  }
}
