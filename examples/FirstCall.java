import com.kimss.AgentRunResult;
import com.kimss.KimssClient;

/**
 * Clean-env first call. Requires:
 *   export KIMSS_API_KEY=...
 *   export KIMSS_ASSISTANT_ID=asst_...
 * Optional: KIMSS_BASE_URL=https://api.kimss.ai
 */
public class FirstCall {
  public static void main(String[] args) {
    String assistantId = System.getenv("KIMSS_ASSISTANT_ID");
    if (assistantId == null || assistantId.isBlank()) {
      System.err.println("Set KIMSS_ASSISTANT_ID");
      System.exit(1);
    }
    KimssClient client = KimssClient.fromEnv();
    AgentRunResult result = client.agents().run(assistantId, "Hello from kimss-java!");
    System.out.println(result.text());
  }
}
