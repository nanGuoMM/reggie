package com.nanGuoMM.reggie.backend.config;

import com.nanGuoMM.reggie.backend.interceptor.EmployeeLoginCheckInterceptor;
import com.nanGuoMM.reggie.backend.json.JacksonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private EmployeeLoginCheckInterceptor employeeLoginCheckInterceptor;

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").
                addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // swagger
        String[] swaggerPatterns = new String[]{"/swagger-resources/**", "/webjars/**", "/v2/**",
                "/swagger-ui.html/**", "/api", "/api-docs", "/api-docs/**", "/doc.html/**"};
        //静态资源
        String staticPattern = "/backend/**";
        //登录退出注册操作的信号
        String[] loginPatterns = new String[] {"/employee/login","/employee/logout"};
        //给员工放行的拦截器
        registry.addInterceptor(employeeLoginCheckInterceptor).addPathPatterns("/**")
                //排除资源
                .excludePathPatterns(staticPattern)
                .excludePathPatterns(swaggerPatterns)
                .excludePathPatterns(loginPatterns);

    }
    /**
     * 扩展mvc框架的消息转换器
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);
    }
}
