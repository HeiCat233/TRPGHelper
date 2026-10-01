/*
  TRPG 规则书知识路由初始化脚本（TRPGHelper）

  适用文档示例：
  - D&D 5e 规则书（玩家手册 PHB / SRD 5.1 等任一中文或英文版本）
  - COC 7版 守秘人手册 / 快速开始规则

  使用顺序：
  1. 先在管理台上传规则书（版权规则书请本地自备，勿提交到仓库）。
  2. 进入每份文档详情页，完成“确认策略方案”和“构建索引执行”。
  3. 回到文档列表或数据库中拿到规则书的 document_id。
  4. 替换下方两个变量 @doc_dnd5e_id / @doc_coc7_id。
  5. 执行本脚本（可重复执行）。

  如何扩展新的规则体系（如 Pathfinder 2e、Fate、Savage Worlds）：
  - 仿照第 3/4/6 节，为新的 scope_code（如 pf2e_rules）增加范围、主题和关联即可。
  - scope 是防混错的第一道闸门：不同体系的规则书务必登记到不同 scope。
  - 同一体系的不同版本（如 DND 3.5e 与 5e）也必须使用不同 scope。

  注意：
  - 本脚本会更新 nexus_agent_document 的知识域编码、名称、业务分类和标签。
  - 本脚本会写入知识范围、知识主题、文档画像、主题文档关联。
  - 本脚本使用 INSERT ... ON DUPLICATE KEY UPDATE，可重复执行。
  - 本脚本不会修改文档解析、策略方案、索引状态、chunk、向量库数据。
*/

USE nexus_agent;

START TRANSACTION;

/* =========================================================
   0. 请先替换这里的文档 ID
   ========================================================= */

SET @doc_dnd5e_id = 0; -- TODO: 替换为 D&D 5e 规则书（如 SRD 5.1）的 document_id
SET @doc_coc7_id = 0;  -- TODO: 替换为 COC 7版规则书的 document_id

/*
  如果你不确定文档 ID，可以先执行下面的查询，再把查出来的 id 填到上面变量里：

  SELECT id, document_name, original_file_name, index_status, last_index_task_id
  FROM nexus_agent_document
  WHERE status = 1
  ORDER BY create_time DESC;
*/

/* =========================================================
   1. 固定配置编码
   ========================================================= */

SET @scope_dnd5e_code = 'dnd5e_rules';
SET @scope_dnd5e_name = 'DND 5e 规则';
SET @scope_coc7_code = 'coc7_rules';
SET @scope_coc7_name = 'COC 7版规则';

/*
  这些 id 只用于新插入范围、主题、关系、画像时。
  如果你的数据库里极端情况下已经占用了这些 id，可以把 @base_id 改成其他未使用的大整数。
*/
SET @base_id = 8800052000000000000;

/* =========================================================
   2. 更新规则书主表元数据
   ========================================================= */

UPDATE nexus_agent_document
SET
    knowledge_scope_code = @scope_dnd5e_code,
    knowledge_scope_name = @scope_dnd5e_name,
    business_category = 'TRPG 规则书',
    document_tags = 'DND,龙与地下城,5e,d20,规则书,玩家手册,SRD',
    edit_time = NOW()
WHERE id = @doc_dnd5e_id
  AND status = 1;

UPDATE nexus_agent_document
SET
    knowledge_scope_code = @scope_coc7_code,
    knowledge_scope_name = @scope_coc7_name,
    business_category = 'TRPG 规则书',
    document_tags = 'COC,克苏鲁的呼唤,7版,d100,规则书,守秘人,调查员',
    edit_time = NOW()
WHERE id = @doc_coc7_id
  AND status = 1;

/* =========================================================
   3. 知识范围配置（规则体系）
   ========================================================= */

INSERT INTO nexus_agent_knowledge_scope_node (
    id, scope_code, scope_name, parent_scope_code, description, aliases, examples, sort_order,
    create_time, edit_time, status
)
VALUES
(
    @base_id + 1,
    @scope_dnd5e_code,
    @scope_dnd5e_name,
    NULL,
    '用于承接《龙与地下城》第五版（D&D 5e / SRD 5.1）的规则问题：战斗与行动经济、法术与施法、豁免与检定、伤害与治疗、职业能力等。注意与其他版本（3.5e、4e、Pathfinder）严格区分。',
    'DND,D&D,龙与地下城,5e,第五版,5e规则,dnd5e,SRD',
    '["火球术命中后要怎么办","先攻顺序怎么决定","豁免检定怎么通过","动作和附赠动作有什么区别"]',
    10,
    NOW(), NOW(), 1
),
(
    @base_id + 2,
    @scope_coc7_code,
    @scope_coc7_name,
    NULL,
    '用于承接《克苏鲁的呼唤》第七版（COC 7th）的规则问题：技能检定、理智值 SAN、战斗与伤害、调查与线索、追逐等。注意与 6 版规则（如最大成功率上限）严格区分。',
    'COC,克苏鲁的呼唤,克苏鲁跑团,Call of Cthulhu,7版,第七版,coc7',
    '["侦查检定怎么算成功","理智检定失败会怎样","战斗回合怎么进行","奖励骰和惩罚骰是什么"]',
    20,
    NOW(), NOW(), 1
)
ON DUPLICATE KEY UPDATE
    scope_name = VALUES(scope_name),
    parent_scope_code = VALUES(parent_scope_code),
    description = VALUES(description),
    aliases = VALUES(aliases),
    examples = VALUES(examples),
    sort_order = VALUES(sort_order),
    edit_time = NOW(),
    status = 1;

/* =========================================================
   4. 知识主题配置（规则书章节域）
   answer_shape 固定为 list / explain / steps
   execution_preference 固定为 retrieval / graph_assist
   ========================================================= */

INSERT INTO nexus_agent_knowledge_topic_node (
    id, topic_code, topic_name, scope_code, description, aliases, examples,
    answer_shape, execution_preference, sort_order,
    create_time, edit_time, status
)
VALUES
(
    @base_id + 101,
    'dnd5e_combat_rules',
    '战斗与行动经济',
    @scope_dnd5e_code,
    '回答 DND 5e 的先攻顺序、回合结构、动作/附赠动作/反应、移动与借机攻击等战斗流程问题。',
    '战斗,先攻,回合,动作,附赠动作,反应,借机攻击',
    '["先攻顺序怎么决定","一个回合能做什么","反应什么时候能用"]',
    'steps',
    'retrieval',
    10,
    NOW(), NOW(), 1
),
(
    @base_id + 102,
    'dnd5e_spells',
    '法术与施法',
    @scope_dnd5e_code,
    '回答 DND 5e 的法术位、施法成分、专注，以及具体法术（如火球术、魔法飞弹）的效果、豁免与伤害。',
    '法术,施法,法术位,火球术,专注,法术成分',
    '["火球术命中后要怎么办","专注被打破会怎样","仪式施法是什么"]',
    'explain',
    'retrieval',
    20,
    NOW(), NOW(), 1
),
(
    @base_id + 103,
    'dnd5e_saving_throws_checks',
    '豁免与检定',
    @scope_dnd5e_code,
    '回答 DND 5e 的六项属性检定、技能检定、豁免检定、DC 的计算方式（8+熟练+调整值）与优劣势规则。',
    '豁免,检定,DC,优势,劣势,熟练加值',
    '["敏捷豁免怎么算","DC 是怎么定出来的","优势和劣势同时存在怎么办"]',
    'explain',
    'retrieval',
    30,
    NOW(), NOW(), 1
),
(
    @base_id + 104,
    'dnd5e_damage_healing',
    '伤害治疗与濒死',
    @scope_dnd5e_code,
    '回答 DND 5e 的伤害类型与抗性、临时生命值、治疗、0 生命值、死亡豁免与稳定伤势。',
    '伤害,治疗,濒死,死亡豁免,稳定伤势,临时生命值',
    '["扣到 0 血会怎样","死亡豁免怎么掷","怎么救倒地的队友"]',
    'steps',
    'retrieval',
    40,
    NOW(), NOW(), 1
),
(
    @base_id + 105,
    'dnd5e_class_features',
    '职业能力与资源',
    @scope_dnd5e_code,
    '回答 DND 5e 各职业的核心能力、熟练项、每日资源（如狂暴次数、超魔法点）的使用规则。',
    '职业,野蛮人,战士,法师,狂暴,超魔法,职业能力',
    '["狂暴能维持多久","法师抄录卷轴要花多少钱","武僧的气点怎么用"]',
    'list',
    'retrieval',
    50,
    NOW(), NOW(), 1
),
(
    @base_id + 201,
    'coc7_skill_checks',
    '技能检定',
    @scope_coc7_code,
    '回答 COC 7版 的 d100 检定流程、常规/困难/极难成功、奖励骰与惩罚骰、对标值与对立检定。',
    '技能检定,d100,困难成功,极难成功,奖励骰,惩罚骰',
    '["侦查检定怎么算成功","奖励骰怎么掷","对立检定谁赢"]',
    'explain',
    'retrieval',
    10,
    NOW(), NOW(), 1
),
(
    @base_id + 202,
    'coc7_sanity',
    '理智值与疯狂',
    @scope_coc7_code,
    '回答 COC 7版 的理智检定流程、SAN 值扣除（一次/每日上限）、临时疯狂、不定疯狂与恐惧症状。',
    '理智,SAN,理智检定,疯狂,恐惧症状',
    '["理智检定失败会怎样","0/1d6 是什么意思","临时疯狂持续多久"]',
    'explain',
    'retrieval',
    20,
    NOW(), NOW(), 1
),
(
    @base_id + 203,
    'coc7_combat',
    '战斗与伤害',
    @scope_coc7_code,
    '回答 COC 7版 的战斗回合顺序、斗殴与射击流程、命中判定、伤害加值 DB 与重伤后果。',
    '战斗,斗殴,射击,伤害加值,DB,重伤',
    '["战斗一轮怎么进行","开枪命中判定怎么做","重伤有什么后果"]',
    'steps',
    'retrieval',
    30,
    NOW(), NOW(), 1
),
(
    @base_id + 204,
    'coc7_investigation',
    '调查与线索',
    @scope_coc7_code,
    '回答 COC 7版 调查员常用的侦查、图书馆使用、聆听、心理学等技能在跑团中的用途与用法。',
    '调查,线索,侦查,图书馆使用,聆听,心理学',
    '["侦查和图书馆使用有什么区别","怎么找到隐藏线索","心理学检定什么时候用"]',
    'list',
    'retrieval',
    40,
    NOW(), NOW(), 1
)
ON DUPLICATE KEY UPDATE
    topic_name = VALUES(topic_name),
    scope_code = VALUES(scope_code),
    description = VALUES(description),
    aliases = VALUES(aliases),
    examples = VALUES(examples),
    answer_shape = VALUES(answer_shape),
    execution_preference = VALUES(execution_preference),
    sort_order = VALUES(sort_order),
    edit_time = NOW(),
    status = 1;

/* =========================================================
   5. 文档画像配置
   说明：如果系统已自动生成画像，这里会覆盖为更贴合规则书的手工画像。
   ========================================================= */

INSERT INTO nexus_agent_document_profile (
    id, document_id, profile_version, document_summary, document_type, core_topics, example_questions,
    graph_friendly, supports_graph_outline, supports_item_lookup, supports_graph_assist,
    profile_source, profile_status, error_msg,
    create_time, edit_time, status
)
VALUES
(
    @base_id + 301,
    @doc_dnd5e_id,
    1,
    '《龙与地下城》第五版（D&D 5e / SRD 5.1）规则书，覆盖战斗与行动经济、法术与施法、豁免与检定、伤害治疗与濒死、职业能力与资源等核心规则。',
    'rulebook',
    '["战斗与行动经济","法术与施法","豁免与检定","伤害治疗与濒死","职业能力与资源"]',
    '["火球术命中后要怎么办","先攻顺序怎么决定","敏捷豁免怎么算","扣到 0 血会怎样","死亡豁免怎么掷"]',
    1, 1, 1, 1,
    'manual',
    2,
    NULL,
    NOW(), NOW(), 1
),
(
    @base_id + 302,
    @doc_coc7_id,
    1,
    '《克苏鲁的呼唤》第七版（COC 7th）规则书，覆盖 d100 技能检定、理智值 SAN 与疯狂、战斗与伤害、调查与线索等核心规则。',
    'rulebook',
    '["技能检定","理智值与疯狂","战斗与伤害","调查与线索"]',
    '["侦查检定怎么算成功","理智检定失败会怎样","战斗一轮怎么进行","奖励骰怎么掷"]',
    1, 1, 1, 1,
    'manual',
    2,
    NULL,
    NOW(), NOW(), 1
)
ON DUPLICATE KEY UPDATE
    profile_version = COALESCE(profile_version, 0) + 1,
    document_summary = VALUES(document_summary),
    document_type = VALUES(document_type),
    core_topics = VALUES(core_topics),
    example_questions = VALUES(example_questions),
    graph_friendly = VALUES(graph_friendly),
    supports_graph_outline = VALUES(supports_graph_outline),
    supports_item_lookup = VALUES(supports_item_lookup),
    supports_graph_assist = VALUES(supports_graph_assist),
    profile_source = VALUES(profile_source),
    profile_status = VALUES(profile_status),
    error_msg = VALUES(error_msg),
    edit_time = NOW(),
    status = 1;

/* =========================================================
   6. 主题文档关联配置
   说明：先清理这些主题下的旧跨文档关联，再写入目标关联。
   ========================================================= */

UPDATE nexus_agent_topic_document_relation
SET status = 0, edit_time = NOW()
WHERE topic_code IN (
    'dnd5e_combat_rules',
    'dnd5e_spells',
    'dnd5e_saving_throws_checks',
    'dnd5e_damage_healing',
    'dnd5e_class_features'
)
AND document_id <> @doc_dnd5e_id;

UPDATE nexus_agent_topic_document_relation
SET status = 0, edit_time = NOW()
WHERE topic_code IN (
    'coc7_skill_checks',
    'coc7_sanity',
    'coc7_combat',
    'coc7_investigation'
)
AND document_id <> @doc_coc7_id;

INSERT INTO nexus_agent_topic_document_relation (
    id, topic_code, document_id, relation_score, relation_source, reason,
    create_time, edit_time, status
)
VALUES
(
    @base_id + 401, 'dnd5e_combat_rules', @doc_dnd5e_id, 0.9800, 'manual',
    '该规则书完整描述了 DND 5e 的先攻、回合结构与动作经济。',
    NOW(), NOW(), 1
),
(
    @base_id + 402, 'dnd5e_spells', @doc_dnd5e_id, 0.9800, 'manual',
    '该规则书包含 DND 5e 的施法规则与法术条目（如火球术）。',
    NOW(), NOW(), 1
),
(
    @base_id + 403, 'dnd5e_saving_throws_checks', @doc_dnd5e_id, 0.9700, 'manual',
    '该规则书包含 DND 5e 的检定、豁免与 DC 计算规则。',
    NOW(), NOW(), 1
),
(
    @base_id + 404, 'dnd5e_damage_healing', @doc_dnd5e_id, 0.9700, 'manual',
    '该规则书包含 DND 5e 的伤害、治疗与濒死规则。',
    NOW(), NOW(), 1
),
(
    @base_id + 405, 'dnd5e_class_features', @doc_dnd5e_id, 0.9500, 'manual',
    '该规则书包含 DND 5e 的职业能力与资源描述。',
    NOW(), NOW(), 1
),
(
    @base_id + 501, 'coc7_skill_checks', @doc_coc7_id, 0.9800, 'manual',
    '该规则书完整描述了 COC 7版 的 d100 检定与奖励/惩罚骰。',
    NOW(), NOW(), 1
),
(
    @base_id + 502, 'coc7_sanity', @doc_coc7_id, 0.9800, 'manual',
    '该规则书包含 COC 7版 的理智检定与疯狂规则。',
    NOW(), NOW(), 1
),
(
    @base_id + 503, 'coc7_combat', @doc_coc7_id, 0.9700, 'manual',
    '该规则书包含 COC 7版 的战斗流程与伤害结算。',
    NOW(), NOW(), 1
),
(
    @base_id + 504, 'coc7_investigation', @doc_coc7_id, 0.9500, 'manual',
    '该规则书包含 COC 7版 调查类技能的用途与用法。',
    NOW(), NOW(), 1
)
ON DUPLICATE KEY UPDATE
    relation_score = VALUES(relation_score),
    relation_source = VALUES(relation_source),
    reason = VALUES(reason),
    edit_time = NOW(),
    status = 1;

/* =========================================================
   7. 执行后检查
   ========================================================= */

SELECT
    id,
    document_name,
    knowledge_scope_code,
    knowledge_scope_name,
    business_category,
    document_tags,
    index_status,
    last_index_task_id
FROM nexus_agent_document
WHERE id IN (@doc_dnd5e_id, @doc_coc7_id);

SELECT
    scope_code,
    scope_name,
    aliases,
    sort_order,
    status
FROM nexus_agent_knowledge_scope_node
WHERE scope_code IN (@scope_dnd5e_code, @scope_coc7_code)
ORDER BY sort_order;

SELECT
    topic_code,
    topic_name,
    scope_code,
    answer_shape,
    execution_preference,
    sort_order,
    status
FROM nexus_agent_knowledge_topic_node
WHERE scope_code IN (@scope_dnd5e_code, @scope_coc7_code)
ORDER BY scope_code, sort_order;

COMMIT;
