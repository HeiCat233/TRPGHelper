# 演示语料说明

本目录提供上传演示用的示例文档，均为可公开分发的内容：

| 文件 | 来源 | 授权 |
| :-- | :-- | :-- |
| `DND5e规则-SRD51-战斗顺序与行动经济.md` | 翻译改编自 [SRD 5.1](https://dnd.wizards.com/resources/systems-reference-document)（Wizards of the Coast LLC） | [CC-BY-4.0](https://creativecommons.org/licenses/by/4.0/legalcode) |
| `DND5e规则-SRD51-常用法术-火球术.md` | 翻译改编自 [SRD 5.1](https://dnd.wizards.com/resources/systems-reference-document)（Wizards of the Coast LLC） | [CC-BY-4.0](https://creativecommons.org/licenses/by/4.0/legalcode) |
| `TRPG新手入门-DND与COC骰子机制对比.md` | TRPGHelper 项目自写科普 | 与仓库许可一致（Apache 2.0） |

## 使用方式

1. 在管理后台「文档接入」上传本目录的 md 文件；
2. 在文档详情页确认切块策略并构建索引；
3. 编辑 `sql/Mysql/TRPG知识路由初始化脚本.sql`，填入上传后的 document_id 并执行；
4. 回到对话页即可测试，例如："火球术命中后要怎么办？"

## 版权提醒

商业规则书（如《玩家手册》《守秘人手册》）不在本仓库收录范围，请自行购买后**本地**上传使用，勿提交到仓库。
