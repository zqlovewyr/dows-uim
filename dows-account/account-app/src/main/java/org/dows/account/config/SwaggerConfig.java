package org.dows.account.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EnableKnife4j
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket accountAdminApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(true).select()
                .apis(RequestHandlerSelectors.basePackage("org.dows.accout.rest")).paths(PathSelectors.any()).build().groupName("账号端");
    }

    @Bean
    public Docket storeAdminApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(true).select()
                .apis(RequestHandlerSelectors.basePackage("org.dows.uim.admin.rest")).paths(PathSelectors.any()).build().groupName("管理端");
    }

    @Bean
    public Docket storeTenantApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(true).select()
                .apis(RequestHandlerSelectors.basePackage("org.dows.uim.tenant.rest")).paths(PathSelectors.any()).build().groupName("租户端");
    }

    @Bean
    public Docket storeUserApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(true).select()
                .apis(RequestHandlerSelectors.basePackage("org.dows.user.rest")).paths(PathSelectors.any()).build().groupName("用户端");
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("lait", "", "lait.zhang@gmail.com");
        return new ApiInfoBuilder().title("dows saas uim").contact(contact).version("3.0").description("user identifier manager").version("1.0.0").build();
    }


    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder().name("appid").description("应用id").required(true).in(ParameterType.QUERY).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false).build());
        parameters.add(new RequestParameterBuilder().name("token").description("token").required(true).in(ParameterType.QUERY).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false).build());
        return parameters;
    }

    //生成通用响应信息
    private List<Response> getGlobalResonseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("找不到资源").build());
        return responseList;
    }


    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }

}
