<p align="center">
  <strong>TRPGHelper</strong><br />
  <span>面向跑团场景的规则书问答助手 —— 上传规则书、精准检索、规则判定推理</span>
</p>

<p align="center">
  <a href="./LICENSE"><img alt="License" src="https://img.shields.io/badge/license-Apache--2.0-4a9b8f?style=flat-square" /></a>
  <img alt="Java" src="https://img.shields.io/badge/Java-17-df5b46?style=flat-square&logo=openjdk" />
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.5-6db33f?style=flat-square&logo=springboot&logoColor=white" />
  <img alt="Spring AI" src="https://img.shields.io/badge/Spring%20AI-1.1-2d6a8a?style=flat-square" />
  <img alt="Vue 3" src="https://img.shields.io/badge/Vue-3-42b883?style=flat-square&logo=vuedotjs&logoColor=white" />
</p>

# TRPGHelper 是什么？

**TRPGHelper** 是一个面向 **TRPG（桌面角色扮演游戏）** 场景的规则书问答助手，专为不熟悉 DND（龙与地下城）、COC（克苏鲁的呼唤）等规则体系的玩家和新手 DM 设计。

核心场景：跑团过程中，你不需要翻几百页规则书——直接问它：

> **"我释放火球术命中了敌人，接下来要怎么办？"**

Agent 会检索你上传的规则书，给出类似这样的回答：

1. **法术效果**：火球术（DND 5e）在以施法点为中心 20 英尺半径范围内造成火焰伤害；
2. **判定流程**：范围内每个目标进行敏捷豁免，DC 为你的法术豁免 DC（8 + 熟练加值 + 关键属性调整值）；
3. **结果结算**：豁免失败受到 **8d6** 火焰伤害，成功则**减半（4d6）**；
4. **下一步建议**：告诉我你的敏捷调整值，我帮你确认豁免结果；掷完骰子把结果告诉我，我帮你计算扣血。

# 核心特性

- **规则书精准检索**：PGVector 向量 + Elasticsearch 关键词双通道并行检索，RRF 融合排序，Parent-Child 块聚合（小块命中、大块回答）。
- **知识路由三级漏斗**：多套规则书共存时（如 DND 5e 与 COC 7版），提问先经过 Scope → Topic → Document 三级路由锁定规则体系，**同名规则不同体系绝不混淆**；路由置信度低时自动转入澄清追问（"你用的是哪个体系/版本？"）。
- **规则判定推理**：回答遵循固定结构"规则效果 → 判定流程（掷什么骰、DC/成功标准）→ 结果结算（成功/失败各自后果）→ 下一步建议"，并结合你在对话中提供的角色属性（敏捷调整值、豁免加值等）代入计算。
- **文档自助接入**：上传规则书 PDF/Markdown → 自动解析 → 确认切块策略 → 一键构建向量 + 关键词双索引，全程任务日志可视。
- **三种回答模式**：指定规则书（精准问答）/ 自动知识（系统路由）/ 开放提问（ReAct Agent + Tavily 联网搜索）。
- **证据驱动、无证据短路**：回答必须基于规则书证据并标注出处；检索不到证据时直接说明，不凭空编造规则。
- **SSE 流式输出**：正文、思考过程、引用来源、推荐追问分事件实时推送。
- **会话记忆**：滑动窗口记忆（默认），角色属性在会话内持续有效。
- **全链路可观测**：每次对话的路由判定、检索命中、模型调用耗时全部落库，管理后台可逐轮回放。

# 技术栈

| 层 | 技术 |
| :-- | :-- |
| 后端 | Java 17 · Spring Boot 3.5 · Spring AI 1.1 · Spring AI Alibaba Agent Framework（ReactAgent / Checkpoint） |
| 检索 | PostgreSQL + PGVector（向量）· Elasticsearch + IK（关键词）· RRF 融合 · 可选外部 Rerank |
| 中间件 | MySQL（业务 + Agent 检查点）· Redis（会话租约）· Kafka（文档流水线）· MinIO（文档存储） |
| 模型 | 阿里云百炼 DashScope（OpenAI 兼容协议）：qwen-plus-latest / text-embedding-v4，可替换任意兼容服务 |
| 前端 | Vue 3 · Vite · Tailwind CSS 4 · shadcn-vue · 原生 fetch + SSE 流式解析 |

> 相较上游项目，本仓库裁剪了 Neo4j 结构图谱依赖（结构查询走 MySQL 结构节点表），并移除了教学示例工程。

# 快速开始

## 1. 准备中间件

本地启动以下服务（默认地址均为 127.0.0.1，可在 `application.yaml` 修改）：

| 服务 | 端口 | 用途 |
| :-- | :-- | :-- |
| MySQL | 3306 | 业务数据 + Agent Checkpoint |
| Redis | 6379 | 会话租约互斥 |
| Kafka | 9092 | 文档解析/索引异步流水线 |
| MinIO | 9000 | 规则书原文件与解析文本存储 |
| PostgreSQL（含 pgvector 扩展） | 5432 | 文档切块向量 |
| Elasticsearch（建议装 IK 分词） | 9200 | 关键词检索、导航索引、路由索引 |

## 2. 初始化数据库

```sql
-- MySQL：执行 sql/Mysql/create_database_mysql.sql 与 create_table_mysql.sql
-- PostgreSQL：执行 sql/PostgresSql/ 下建库建表脚本
```

## 3. 配置模型与密钥

```bash
# 阿里云百炼 API Key（必需）
export ALI_BAI_LIAN_API_KEY=sk-xxxx
# Tavily 联网搜索 API Key（开放提问模式使用，可选）
export TAVILY_API_KEY=tvly-xxxx
# MySQL 密码（默认 123456）
export MYSQL_PASSWORD=123456
```

## 4. 启动

```bash
# 后端（端口 9082）
mvn spring-boot:run -pl nexus-agent-business/nexus-agent-business-chat

# 前端（端口 5173，/api 等前缀自动代理到 9082）
cd vue && npm install && npm run dev
```

浏览器访问 `http://localhost:5173`。管理后台默认账号密码 `admin / admin123456`（可通过环境变量 `NEXUS_AGENT_ADMIN_USERNAME/PASSWORD` 覆盖）。

# 规则书接入流程

1. **上传规则书**：管理后台 → 文档接入 → 上传（支持 pdf / docx / md / txt 等），系统自动解析并生成切块策略；
2. **确认策略并建索引**：在文档详情页确认父块/子块切块方案 → 构建索引（向量 + 关键词双引擎）；
3. **初始化知识路由**：编辑 `sql/Mysql/TRPG知识路由初始化脚本.sql`，把规则书对应的 `document_id`（上传后可在文档列表获取）填入占位变量后执行，登记规则书所属的体系（scope）与主题（topic）；
4. **开问**：回到对话页，模式选"自动知识"，直接提问。

> 版权提示：商业规则书（如 DND PHB、COC 守秘人手册）请自行购买后**本地**上传使用，本仓库不收录；仓库内演示语料为开放授权（CC-BY）的 D&D 5e SRD 5.1 章节。

# 关于检索精确性

不同体系、不同版本的规则书存在大量同名但规则完全不同的概念（火球术在 DND 5e 是 8d6 豁免减半，在 DND 3.5e/PF 是 1d6/施法者等级；DND 用 d20 对抗 DC，COC 用 d100 低于技能值）。为此本项目的防错设计：

1. **路由防混**：每本规则书登记到独立的体系 scope，提问先路由再检索，跨体系同名词不会被混检；
2. **澄清兜底**：路由置信度低或前两名分差过小且跨体系时，Agent 主动追问体系与版本，而不是猜；
3. **版本意识提示词**：系统提示词强制要求回答标注体系与版本，禁止混用不同版本的规则；
4. **可选 Rerank 精排**：接入 SiliconFlow bge-reranker-v2-m3（`app.chat.rag.rerank.enabled: true`）进一步提升候选排序精度。

# 项目结构

```
TRPGHelper/
├── nexus-agent-business/
│   └── nexus-agent-business-chat/     # 唯一可运行后端应用（对话 Agent + 文档治理 + 知识路由）
├── nexus-agent-common/                # 公共基础（工具 / Web / MyBatis-Plus / Knife4j）
├── nexus-agent-id-generator-framework/ # 分布式 ID 生成器
├── nexus-agent-redisson-framework/    # Redisson 封装（分布式锁 / 租约 / 幂等）
├── vue/                               # 前端（对话页 + 管理后台）
├── sql/                               # MySQL / PostgreSQL 建库建表 + TRPG 知识路由初始化脚本
└── 需要的例子演示/                     # 演示语料（D&D 5e SRD 5.1 章节）
```

# 二次开发说明与致谢

本项目基于 [JavaUp（阿星不是程序员）](https://gitee.com/shining-stars-l/super-ai) 的 **nexus-agent（Super Nexus Agent）** 二次开发，保留其 Apache License 2.0 许可，向原作者致谢。

相对上游的主要改动：

- 品牌与文案全面适配 TRPG 跑团场景；
- 系统提示词 / RAG 回答提示词 / 问题改写提示词重写为规则助手定位（判定结构化输出、版本意识、属性代入）；
- 移除 Neo4j 结构图谱中间件（结构查询改走 MySQL 结构节点表），减少一个部署依赖；
- 移除教学示例工程 `ai-example` 与未使用的 redis 工具模块；
- 新增 TRPG 知识路由初始化脚本与 SRD 5.1 演示语料。

# 许可

[Apache License 2.0](./LICENSE)
