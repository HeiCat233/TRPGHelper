package org.javaup.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: TRPGHelper - TRPG 跑团规则书问答助手（基于 nexus-agent 二次开发）
 * @description: 配置类
 * @author: 阿星不是程序员
 **/

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("TRPGHelper API")
                        .version("1.0")
                        .description("TRPG 跑团规则书问答助手接口文档")
                        .contact(new Contact()
                                .name("TRPGHelper")
                        ));
    }
}
