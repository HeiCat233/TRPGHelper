package org.javaup.ai.chatagent.rag.retrieve.channel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.document.Document;

import java.util.List;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 结果对象
 * @author: 阿星不是程序员
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrievalChannelResult {

    private String channelName;

    private List<Document> documents;
}
