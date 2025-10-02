package co.com.bancolombia.api;

import co.com.bancolombia.usecase.response.ReportResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    private static final String BASE_URL = "/v1/api";

    @Bean
    @RouterOperation(
        path = "/v1/api/reports",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        method = RequestMethod.GET,
        beanClass = Handler.class,
        beanMethod = "findAllReports",
        operation = @Operation(
            operationId = "findAllReports",
            summary = "Listar reportes de bootcamps",
            description = "Obtiene el listado de reportes agregados por bootcamp, " +
                "incluyendo métricas de capacidades, tecnologías y personas inscritas.",
            tags = {"Reports"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente",
                    content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = ReportResponse.class)),
                        examples = @ExampleObject(
                            name = "Success Response",
                            summary = "Lista de reportes",
                            value = "[\n" +
                                "  {\n" +
                                "    \"bootcampId\": 100,\n" +
                                "    \"name\": \"Backend Fundamentals\",\n" +
                                "    \"description\": \"Intro to backend engineering\",\n" +
                                "    \"launchDate\": \"2024-09-01T00:00:00.000+00:00\",\n" +
                                "    \"duration\": 12,\n" +
                                "    \"capacitiesCount\": 5,\n" +
                                "    \"technologiesCount\": 12,\n" +
                                "    \"peopleEnrolledCount\": 120\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"bootcampId\": 101,\n" +
                                "    \"name\": \"Frontend Masters\",\n" +
                                "    \"description\": \"Advanced frontend topics\",\n" +
                                "    \"launchDate\": \"2024-10-15T00:00:00.000+00:00\",\n" +
                                "    \"duration\": 10,\n" +
                                "    \"capacitiesCount\": 4,\n" +
                                "    \"technologiesCount\": 9,\n" +
                                "    \"peopleEnrolledCount\": 95\n" +
                                "  }\n" +
                                "]"
                        )
                    )
                ),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @ExampleObject(
                            name = "Internal Error",
                            summary = "Error interno",
                            value = "{\n" +
                                "  \"error\": \"INTERNAL_ERROR\",\n" +
                                "  \"message\": \"An unexpected error occurred\"\n" +
                                "}"
                        )
                    )
                )
            }
        )
    )
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET(BASE_URL + "/reports"), handler::findAllReports);
    }
}
