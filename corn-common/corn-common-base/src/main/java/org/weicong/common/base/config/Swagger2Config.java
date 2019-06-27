/**
 * 
 */
package org.weicong.common.base.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description 访问接口平台的地址 http://localhost:相应服务端口/swagger-ui.html
 * @author weicong
 * @time 2019年6月9日
 * @version 1.0
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

	@Bean
	public Docket api() {
		ParameterBuilder param = new ParameterBuilder();
		List<Parameter> list = new ArrayList<Parameter>();
		param.name("permit").description("用户访问凭证").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		list.add(param.build());

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
				.globalOperationParameters(list);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("corn restful api").description("corn 在线接口文档")
				// 服务条款网址
				.termsOfServiceUrl("http://localhost/").version("1.0.0")
				.contact(new Contact(" 魏 聪 ", "", "weicongmail@foxmail.com")).build();
	}

}
