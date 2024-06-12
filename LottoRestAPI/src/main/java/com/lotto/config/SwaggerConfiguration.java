package com.lotto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//Swagger-UI 확인
//http://localhost/swagger-ui.html

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().title("Lotto API 명세서").description(
				"<h3>Lotto Reference for Developers</h3>Swagger를 이용한 Board API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">")
				.version("v1").contact(new io.swagger.v3.oas.models.info.Contact());

		return new OpenAPI().components(new Components()).info(info);
	}
}
