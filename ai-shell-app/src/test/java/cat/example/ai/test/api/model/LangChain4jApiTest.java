package cat.example.ai.test.api.model;

import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class LangChain4jApiTest {

    public static void main(String[] args) {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://apihub.agnes-ai.com//v1")
                .apiKey(Objects.requireNonNull(System.getenv("AGNES_AI_API_KEY"), "Set AGNES_AI_API_KEY"))
                .modelName("agnes-2.5-flash")
                .build();

        String chat = model.chat("hi 你好哇!");
        log.info("测试结果:{}", chat);
    }

}
