package awd.cloud.suppers.interfaces.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicate;

import awd.framework.common.utils.OpenAPI;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger2的配置类
 *
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Value("${swagger2.package.interfaces}")
	private String interfacePackage;
	
	@Value("${swagger2.package.manager}")
    private String managerPackage;
	
	@Value("${swagger2.package.jls}")
	private String jlsPackage;
	
	@Value("${swagger2.package.kss}")
	private String kssPackage;
	
	@Value("${swagger2.package.jds}")
	private String jdsPackage;
	
	@Value("${swagger2.show.dev}")
	private String dev;
	
	@Value("${swagger2.show.pro}")
	private String pro;
	
	@Value("${spring.application.name}")
    private String title;

    /**
     * 定义api组，
     */
	/**
	 * 内部接口分组
	 * @return
	 */
    @Bean
    @ConditionalOnExpression("${swagger2.show.dev:true}")
    public Docket innerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("innerApi")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("awd.cloud.suppers.interfaces.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(innerApiInfo());
    }
    
    private ApiInfo innerApiInfo() {
    	return new ApiInfoBuilder()
    			.title(title)//大标题
    			.description("监管云平台内部api文档")//详细描述
    			.termsOfServiceUrl("http://www.njawd.com/")
    			.license("所有公共api")
    			.version("1.0")
    			.build();
    }

    /**
     * 外部接口分组
     * @return
     */
    @Bean
    @ConditionalOnExpression("${swagger2.show.pro:true}")
    public Docket openApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                if (input.isAnnotatedWith(OpenAPI.class))//只有添加了ApiOperation注解的method才在API中显示
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("所有公共api")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())//过滤的接口
                .build()
                .apiInfo(openApiInfo());
    }

    private ApiInfo openApiInfo() {
        return new ApiInfoBuilder()
                .title(title)//大标题
                .description("监管云平台对外api文档")//详细描述
                .termsOfServiceUrl("http://www.njawd.com/")
                .license("拘留所").license("看守所").license("戒毒所")
                .version("1.0")
                .build();
    }
    
    /**
     * 接口服务本身api分组
     * @return
     */
    @Bean
    @ConditionalOnExpression("${swagger2.show.pro:true}")
    public Docket interfaceApi() {
    	Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
    		@Override
    		public boolean apply(RequestHandler input) {
    			if (input.isAnnotatedWith(OpenAPI.class))
    				return true;
    			return false;
    		}
    	};
    	return new Docket(DocumentationType.SWAGGER_2)
    			.groupName("接口服务管理api")
    			.genericModelSubstitutes(DeferredResult.class)
    			.useDefaultResponseMessages(false)
    			.forCodeGeneration(false)
    			.select()
    			.apis(predicate)
    			.apis(RequestHandlerSelectors.basePackage(interfacePackage))
    			.paths(PathSelectors.any())
    			.build()
    			.apiInfo(interfaceApiInfo());
    }
    
    private ApiInfo interfaceApiInfo() {
    	return new ApiInfoBuilder()
    			.title(title)//大标题
    			.description("接口服务管理api文档")//详细描述
    			.termsOfServiceUrl("http://www.njawd.com/")
    			.license("所有公共api")
    			.version("1.0")
    			.build();
    }
    
    /**
     * 公共方法接口分组
     * @return
     */
    @Bean
    @ConditionalOnExpression("${swagger2.show.pro:true}")
    public Docket managerApi() {
    	Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
    		@Override
    		public boolean apply(RequestHandler input) {
    			if (input.isAnnotatedWith(OpenAPI.class))
    				return true;
    			return false;
    		}
    	};
    	return new Docket(DocumentationType.SWAGGER_2)
    			.groupName("公共服务管理api")
    			.genericModelSubstitutes(DeferredResult.class)
    			.useDefaultResponseMessages(false)
    			.forCodeGeneration(false)
    			.select()
    			.apis(predicate)
                .apis(RequestHandlerSelectors.basePackage(managerPackage))
                .paths(PathSelectors.any())
    			.build()
    			.apiInfo(managerApiInfo());
    }
    
    private ApiInfo managerApiInfo() {
    	return new ApiInfoBuilder()
    			.title(title)//大标题
    			.description("公共服务管理api文档")//详细描述
    			.termsOfServiceUrl("http://www.njawd.com/")
    			.license("所有公共api")
    			.version("1.0")
    			.build();
    }
    
    /**
     * 拘留所接口分组
     * @return
     */
    @Bean
    @ConditionalOnExpression("${swagger2.show.pro:true}")
    public Docket jlsApi() {
    	Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
    		@Override
    		public boolean apply(RequestHandler input) {
    			if (input.isAnnotatedWith(OpenAPI.class))
    				return true;
    			return false;
    		}
    	};
    	return new Docket(DocumentationType.SWAGGER_2)
    			.groupName("拘留所api")
    			.genericModelSubstitutes(DeferredResult.class)
    			.useDefaultResponseMessages(false)
    			.forCodeGeneration(false)
    			.select()
    			.apis(predicate)
                .apis(RequestHandlerSelectors.basePackage(jlsPackage))
                .paths(PathSelectors.any())
    			.build()
    			.apiInfo(jlsApiInfo());
    }
    
    private ApiInfo jlsApiInfo() {
    	return new ApiInfoBuilder()
    			.title(title)//大标题
    			.description("拘留所对外相关api文档")//详细描述
    			.termsOfServiceUrl("http://www.njawd.com/")
    			.license("所有公共api")
    			.version("1.0")
    			.build();
    }
    
    /**
     * 看守所接口分组
     * @return
     */
    @Bean
    @ConditionalOnExpression("${swagger2.show.pro:true}")
    public Docket kssApi() {
    	Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
    		@Override
    		public boolean apply(RequestHandler input) {
    			if (input.isAnnotatedWith(OpenAPI.class))
    				return true;
    			return false;
    		}
    	};
    	return new Docket(DocumentationType.SWAGGER_2)
    			.groupName("看守所api")
    			.genericModelSubstitutes(DeferredResult.class)
    			.useDefaultResponseMessages(false)
    			.forCodeGeneration(false)
    			.select()
    			.apis(predicate)
                .apis(RequestHandlerSelectors.basePackage(kssPackage))
                .paths(PathSelectors.any())
    			.build()
    			.apiInfo(kssApiInfo())
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder()
										.code(400)
										.message("服务异常,更新失败")
										.build()));

    }

    private ApiInfo kssApiInfo() {
    	return new ApiInfoBuilder()
    			.title(title)//大标题
    			.description("看守所对外相关api文档")//详细描述
    			.termsOfServiceUrl("http://www.njawd.com/")
    			.license("所有公共api")
    			.version("1.0")
    			.build();
    }
    
    /**
     * 戒毒所接口分组
     * @return
     */
    @Bean
    @ConditionalOnExpression("${swagger2.show.pro:true}")
    public Docket jdsApi() {
    	Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
    		@Override
    		public boolean apply(RequestHandler input) {
    			if (input.isAnnotatedWith(OpenAPI.class))
    				return true;
    			return false;
    		}
    	};
    	return new Docket(DocumentationType.SWAGGER_2)
    			.groupName("戒毒所api")
    			.genericModelSubstitutes(DeferredResult.class)
    			.useDefaultResponseMessages(false)
    			.forCodeGeneration(false)
    			.select()
    			.apis(predicate)
                .apis(RequestHandlerSelectors.basePackage(jdsPackage))
                .paths(PathSelectors.any())
    			.build()
    			.apiInfo(jdsApiInfo());
    }
    private ApiInfo jdsApiInfo() {
    	return new ApiInfoBuilder()
    			.title(title)//大标题
    			.description("戒毒所对外相关api文档")//详细描述
    			.termsOfServiceUrl("http://www.njawd.com/")
    			.license("所有公共api")
    			.version("1.0")
    			.build();
    }


}
