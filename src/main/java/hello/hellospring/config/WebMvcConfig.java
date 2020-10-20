package hello.hellospring.config;

import hello.hellospring.utils.HeaderFilter;
import hello.hellospring.utils.JwtTokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 정적 자원의 메소드들에게서는 SpringSecurity 적용 X -> 정적 자원 설정(MVC)
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/",
            "classpath:/public/", "classpath:/", "classpath:/resources/", "classpath:/META-INF/resources/",
            "classpath:/META-INF/resources/webjars/"};

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // /에 해당하는 url mapping을 /common/test로 포워드
        registry.addViewController("/").setViewName("forward:/index");
        // 우선 순위
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    // 인터셉트 추가
    public void addInterceptore(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor())
                .addPathPatterns("/user/findAll");
        // 전체 사용자 조회(/user/findAll)에서 토큰 검사 진행
    }

    // HeaderFilter -> HeaderWriterFilter
    @Bean
    public FilterRegistrationBean<HeaderFilter> getFilterRegistrationBean() {
        FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<>(createHeaderFilter());
        registrationBean.setOrder(Integer.MIN_VALUE);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public HeaderFilter createHeaderFilter() {
        return new HeaderFilter();
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor();
    }

}
