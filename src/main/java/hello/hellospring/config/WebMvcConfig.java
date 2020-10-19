package hello.hellospring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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

}
