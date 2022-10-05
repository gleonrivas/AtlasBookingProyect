package com.app.atlasultimate.registro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.annotation.PostConstruct;

@Configuration
public class Configuracion {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void extension() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("C:\\Users\\orteg\\IdeaProjects\\atlasUltimate\\src\\main\\resources\\templates\\");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(templateEngine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        templateEngine.addTemplateResolver(resolver);
    }
}
