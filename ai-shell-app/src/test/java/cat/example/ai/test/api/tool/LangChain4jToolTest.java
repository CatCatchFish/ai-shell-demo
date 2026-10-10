package cat.example.ai.test.api.tool;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Objects;

@Slf4j
public class LangChain4jToolTest {

    interface Assistant {
        String chat(String message);
    }

    public static void main(String[] args) {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://apihub.agnes-ai.com/v1")
                .apiKey(Objects.requireNonNull(System.getenv("AGNES_AI_API_KEY"), "Set AGNES_AI_API_KEY"))
                .modelName("agnes-2.5-flash")
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .tools(sseMcpClient())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        String answer = assistant.chat("你哪有哪些工具能力");
        log.info("测试结果:{}", answer);
    }

    /**
     * 百度搜索MCP服务(url)；https://sai.baidu.com/zh/detail/e014c6ffd555697deabf00d058baf388
     * 百度搜索MCP服务(key)；https://console.bce.baidu.com/iam/?_=1753597622044#/iam/apikey/list
     */
    public static McpSyncClient sseMcpClient() {

        // 自己申请 api_key
        HttpClientSseClientTransport sseClientTransport = HttpClientSseClientTransport.builder(Objects.requireNonNull(System.getenv("BAIDU_MCP_BASE_URL"), "Set BAIDU_MCP_BASE_URL"))
                .sseEndpoint(Objects.requireNonNull(System.getenv("BAIDU_MCP_SSE_ENDPOINT"), "Set BAIDU_MCP_SSE_ENDPOINT"))
                .build();

        McpSyncClient mcpSyncClient = McpClient.sync(sseClientTransport).requestTimeout(Duration.ofMinutes(360)).build();
        var init_sse = mcpSyncClient.initialize();
        log.info("Tool SSE MCP Initialized {}", init_sse);

        return mcpSyncClient;
    }

}
