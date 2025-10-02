package co.com.bancolombia.usecase;

import co.com.bancolombia.model.report.Report;
import co.com.bancolombia.model.report.gateway.ReportGateway;
import co.com.bancolombia.model.report.message.CreateBootcampMessage;
import co.com.bancolombia.usecase.exception.BussinessException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateReportUseCaseTest {

    @Test
    void shouldErrorWhenReportAlreadyExists() {
        ReportGateway gateway = Mockito.mock(ReportGateway.class);
        CreateReportUseCase useCase = new CreateReportUseCase(gateway);

        CreateBootcampMessage message = new CreateBootcampMessage(1L, "Name", "Desc", new Date(), 10, 2, 3);
        when(gateway.findById(1L)).thenReturn(Mono.just(new Report(1L, "N", "D", new Date(), 1, 1, 1, 0)));

        Mono<Report> result = useCase.create(message);
        assertThrows(BussinessException.class, () -> result.block());
    }

    @Test
    void shouldCreateWhenNotExists() {
        ReportGateway gateway = Mockito.mock(ReportGateway.class);
        CreateReportUseCase useCase = new CreateReportUseCase(gateway);

        CreateBootcampMessage message = new CreateBootcampMessage(2L, "Name", "Desc", new Date(), 10, 2, 3);
        when(gateway.findById(2L)).thenReturn(Mono.empty());

        Report expected = new Report(2L, "Name", "Desc", message.getLaunchDate(), 10, 2, 3, 0);
        when(gateway.save(any())).thenReturn(Mono.just(expected));

        Report saved = useCase.create(message).block();
        assertThat(saved).isNotNull();
        assertThat(saved.getBootcampId().getValue()).isEqualTo(2L);
        assertThat(saved.getPeopleEnrolledCount().getValue()).isEqualTo(0);
    }
}


