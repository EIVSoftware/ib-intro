package com.ib.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcCfg implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
        registry.addViewController("/starter").setViewName("starter");
        
        registry.addViewController("/admin/usuarios").setViewName("admin/usuarios-listado");
        registry.addViewController("/admin/cursos").setViewName("admin/cursos-listado");
    }
}
