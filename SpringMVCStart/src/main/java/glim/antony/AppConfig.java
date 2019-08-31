package glim.antony;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan("glim.antony")
public class AppConfig implements WebMvcConfigurer {

    /**
     * добавляет обработчик ресурсов. Метод принимает объект класса
     * ResourceHandlerRegistry и добавляет шаблон пути и локацию. Другими словами, на все
     * запросы /resources/** будет вызываться не контроллер, созданный разработчиком, а
     * возвращаться указанный в запросе файл (например, .css или .js ).
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    /**
     * setupViewResolver() — в этом методе создается и настраивается бин, который является тем
     * самым ViewResolver . Вспомним: контроллер возвращает только строку имени jsp-страницы, а
     * DispatcherServlet обращается к данному бину, который формирует полный путь к
     * представлению, прибавляя к его имени параметры, указанные в методах setPrefix и setSuffix .
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

