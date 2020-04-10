package co.com.hotelyando.api.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors
                .basePackage("co.com.hotelyando.api.controller"))
            .paths(PathSelectors.regex("/.*"))
            .build().apiInfo(apiEndPointsInfo())
            .tags(new Tag("Login", "Controlador que gestiona el acceso de usuarios a la aplicación"))
            .tags(new Tag("User", "Controlador que gestiona las acciones de un usuario de un hotel"))
            .tags(new Tag("Sale", "Controlador que gestiona las acciones para una factura"))
            .tags(new Tag("Room", "Controlador que gestiona las acciones para una habitación"))
            .tags(new Tag("RoomType", "Controlador que gestiona las acciones para un tipo de habitación"))
            .tags(new Tag("Hotel", "Controlador que gestiona las acciones de un hotel"))
            .tags(new Tag("Item", "Controlador que gestiona las acciones de un item"))
            .tags(new Tag("Country", "Controlador que gestiona las acciones para un pais"))
            .tags(new Tag("PackageHotel", "Controlador que gestiona las acciones de un paquete de hotel"))
            .tags(new Tag("Person", "Controlador que gestiona las acciones para una persona"))
            .tags(new Tag("Plan", "Controlador que gestiona las acciones de un plan de hotel"))
            .tags(new Tag("Reservation", "Controlador que gestiona las acciones de reserva para un hotel"))
            .tags(new Tag("Role", "Controlador que gestiona las acciones de los roles de usuario"))
        	.tags(new Tag("Report", "Reportes"))
        	.tags(new Tag("Employee", "Controlador que gestiona la información de los empleados de un hotel"));
            
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Gestion Hoteles")
            .description("")
            .contact(new Contact("Hotelyando", "", "soporte@hotelyando.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
}

