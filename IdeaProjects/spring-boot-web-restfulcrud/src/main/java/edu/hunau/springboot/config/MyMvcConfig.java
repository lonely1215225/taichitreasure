package edu.hunau.springboot.config;


import edu.hunau.springboot.component.LoginHandlerInterceptor;
import edu.hunau.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import(DruidConfig.class)
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/webjars/**","/assets/**");
    }
//两种方式，第一种就是下面，第二种@Component命名为localeResolver,因为默认类名MyLocaleResolver,
    // 主要是让springboot知道有LocaleResolver注册了
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
