package co.com.bancolombia.api;

import co.com.bancolombia.usecase.FindAllReportsUseCase;
import co.com.bancolombia.usecase.response.ReportResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Date;

import static org.mockito.Mockito.*;

class RouterRestTest {

    @Test
    void shouldReturnReportsList() {
        FindAllReportsUseCase useCase = mock(FindAllReportsUseCase.class);
        when(useCase.execute()).thenReturn(Flux.just(
            new ReportResponse(1L, "Name", "Desc", new Date(), 10, 2, 3, 4)
        ));

        Handler handler = new Handler(useCase);
        RouterRest router = new RouterRest();

        WebTestClient client = WebTestClient.bindToRouterFunction(router.routerFunction(handler)).build();

        client.get()
            .uri("/v1/api/reports")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType("application/json")
            .expectBody()
            .jsonPath("$[0].bootcampId").isEqualTo(1);
    }
}

