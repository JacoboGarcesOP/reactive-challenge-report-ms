package co.com.bancolombia.api;

import co.com.bancolombia.usecase.FindAllReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final FindAllReportsUseCase findAllReportsUseCase;

    public Mono<ServerResponse> findAllReports(ServerRequest serverRequest) {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(findAllReportsUseCase.execute(), co.com.bancolombia.usecase.response.ReportResponse.class);
    }
}
