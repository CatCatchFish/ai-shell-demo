# xfg-frame-archetype - DDD 脚手架

## 运行 API 示例

`ai-shell-app/src/test/java/cat/example/ai/test/api/` 下的示例需要在运行环境中设置以下变量：

| 环境变量 | 用途 |
| --- | --- |
| `AGNES_AI_API_KEY` | Agnes AI API 密钥，供模型示例使用 |
| `BAIDU_MCP_BASE_URL` | 百度 MCP 服务的基础 URL，供工具示例使用 |
| `BAIDU_MCP_SSE_ENDPOINT` | 百度 MCP 服务的 SSE 路径，供工具示例使用 |

运行模型示例只需设置 `AGNES_AI_API_KEY`；运行工具示例还需设置两个百度 MCP 变量。可在 IntelliJ IDEA 的运行配置中填写环境变量，或在启动 Java 进程的终端中设置。不要把实际密钥或含密钥的 URL 提交到 Git。

