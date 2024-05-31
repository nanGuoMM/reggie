package com.nanGuoMM.reggie.front.config;

import com.nanGuoMM.reggie.front.interceptor.UserLoginCheckInterceptor;
import com.nanGuoMM.reggie.front.json.JacksonObjectMapper;
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
    private UserLoginCheckInterceptor userLoginCheckInterceptor;

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
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
        String[] staticPattern = new String[]{"/front/**","/backend/**"};

        //登录退出注册操作的信号
        String[] loginPatterns = new String[] {"/user/login","/user/register","/user/loginout","/user/code","/error"};

        //给用户放行的拦截器
        registry.addInterceptor(userLoginCheckInterceptor).addPathPatterns("/**")
                .excludePathPatterns(loginPatterns)
                .excludePathPatterns(swaggerPatterns)
                .excludePathPatterns(staticPattern);

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
