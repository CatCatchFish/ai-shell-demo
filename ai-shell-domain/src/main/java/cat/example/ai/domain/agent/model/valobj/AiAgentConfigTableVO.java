package cat.example.ai.domain.agent.model.valobj;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AiAgentConfigTableVO {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 智能体配置
     */
    private Agent agent;

    /**
     * 模型配置
     * 模型配置包含模型API、模型工具、模型智能体、模型工作流
     */
    private Model model;

    @Data
    public static class Agent {
        private String agentName;
        private String agentId;
        private String agentDesc;
    }

    @Data
    public static class Model {

        private AiAPI aiApi;

        private ChatModel chatModel;

        private List<Agent> agents;

        private List<AgentWorkflow> agentWorkflows;


        @Data
        public static class AiAPI {
            private String baseUrl;
            private String apiKey;
            private String completionsPath;
            private String embeddingsPath;
        }

        @Data
        public static class ChatModel {

            @Data
            public static class ToolMcp {

                private SSEServerParameters sse;
                private StdioServerParameters stdio;

                @Data
                public static class SSEServerParameters {
                    private String name;
                    private String baseUrl;
                    private String sseEndpoint;
                    private Integer requestTimeout = 3000;
                }

                @Data
                public static class StdioServerParameters {
                    private String name;
                    private Integer requestTimeout = 3000;

                    @Data
                    public static class ServerParameters {
                        private String command;
                        private List<String> args;
                        private Map<String, String> env;
                    }

                }
            }

        }

        @Data
        public static class Agent {
            private String name;
            private String description;
            private String instruction;
            private String outputKey;
        }

        @Data
        public static class AgentWorkflow {
            /**
             * 类型；loop、parallel、sequential
             */
            private String type;
            private String name;
            private List<String> subAgents;
            private String description;
            private Integer maxIterations = 3;

        }

    }

}
