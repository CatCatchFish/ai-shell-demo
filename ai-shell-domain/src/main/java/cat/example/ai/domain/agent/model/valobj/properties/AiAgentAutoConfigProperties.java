package cat.example.ai.domain.agent.model.valobj.properties;

import cat.example.ai.domain.agent.model.valobj.AiAgentConfigTableVO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "ai.agent.config", ignoreUnknownFields = true)
public class AiAgentAutoConfigProperties {

    /**
     * 是否开启智能体自动配置
     */
    private boolean enabled = false;

    private Map<String, AiAgentConfigTableVO> tables;

}