package co.com.bancolombia.usecase;

import co.com.bancolombia.model.report.Report;
import co.com.bancolombia.model.report.gateway.ReportGateway;
import co.com.bancolombia.usecase.response.ReportResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class FindAllReportsUseCaseTest {

    @Test
    void shouldMapReportToResponse() {
        ReportGateway gateway = Mockito.mock(ReportGateway.class);
        FindAllReportsUseCase useCase = new FindAllReportsUseCase(gateway);

        Report report = new Report(1L, "Name", "Desc", new Date(), 10, 2, 3, 4);
        when(gateway.findAll()).thenReturn(Flux.just(report));

        Flux<ReportResponse> result = useCase.execute();

        ReportResponse first = result.blockFirst();
        assertThat(first).isNotNull();
        assertThat(first.getBootcampId()).isEqualTo(1L);
        assertThat(first.getName()).isEqualTo("Name");
        assertThat(first.getCapacitiesCount()).isEqualTo(2);
        assertThat(first.getPeopleEnrolledCount()).isEqualTo(4);
    }
}


