package co.com.bancolombia.usecase;

import co.com.bancolombia.model.report.Report;
import co.com.bancolombia.model.report.gateway.ReportGateway;
import co.com.bancolombia.model.report.message.UpdateBootcampMessage;
import co.com.bancolombia.usecase.exception.BussinessException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UpdateReportUseCaseTest {

    @Test
    void shouldErrorWhenReportNotFound() {
        ReportGateway gateway = Mockito.mock(ReportGateway.class);
        UpdateReportUseCase useCase = new UpdateReportUseCase(gateway);

        when(gateway.findById(1L)).thenReturn(Mono.empty());

        UpdateBootcampMessage message = new UpdateBootcampMessage(1L, 50);
        Mono<Report> result = useCase.update(message);
        assertThrows(BussinessException.class, () -> result.block());
    }

    @Test
    void shouldUpdatePeopleEnrolledCount() {
        ReportGateway gateway = Mockito.mock(ReportGateway.class);
        UpdateReportUseCase useCase = new UpdateReportUseCase(gateway);

        Report existing = new Report(1L, "N", "D", new Date(), 1, 2, 3, 10);
        when(gateway.findById(1L)).thenReturn(Mono.just(existing));

        when(gateway.update(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        UpdateBootcampMessage message = new UpdateBootcampMessage(1L, 99);
        Report updated = useCase.update(message).block();

        assertThat(updated).isNotNull();
        assertThat(updated.getPeopleEnrolledCount().getValue()).isEqualTo(99);
        assertThat(updated.getBootcampId().getValue()).isEqualTo(1L);
    }
}


