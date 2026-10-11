package cat.example.ai.config;

import cat.example.ai.domain.agent.model.valobj.properties.AiAgentAutoConfigProperties;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(AiAgentAutoConfigProperties.class)
public class AiAgentAutoConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private AiAgentAutoConfigProperties aiAgentAutoConfigProperties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            if (aiAgentAutoConfigProperties.isEnabled()) {
                log.info("开启智能体自动配置:{}", JSON.toJSONString(aiAgentAutoConfigProperties.getTables().values()));
            }
        } catch (Exception e) {
            log.error("智能体自动配置失败", e);
            throw new RuntimeException(e);
        }
    }

}
