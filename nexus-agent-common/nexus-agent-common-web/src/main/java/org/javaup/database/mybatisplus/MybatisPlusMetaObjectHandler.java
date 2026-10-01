package org.javaup.database.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.javaup.util.DateUtils;

import java.util.Date;
/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 处理器
 * @author: 阿星不是程序员
 **/

@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "createTime", DateUtils::now, Date.class);
        this.strictInsertFill(metaObject, "editTime", DateUtils::now, Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.strictUpdateFill(metaObject, "editTime", DateUtils::now, Date.class);
    }
}
