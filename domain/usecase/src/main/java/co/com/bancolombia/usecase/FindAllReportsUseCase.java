package co.com.bancolombia.usecase;

import co.com.bancolombia.model.report.Report;
import co.com.bancolombia.model.report.gateway.ReportGateway;
import reactor.core.publisher.Flux;
import co.com.bancolombia.usecase.response.ReportResponse;

public class FindAllReportsUseCase {

  private final ReportGateway reportGateway;

  public FindAllReportsUseCase(ReportGateway reportGateway) {
    this.reportGateway = reportGateway;
  }

  public Flux<ReportResponse> execute() {
    return reportGateway.findAll()
        .map(this::mapToResponse);
  }

  private ReportResponse mapToResponse(Report report) {
    return new ReportResponse(
        report.getBootcampId().getValue(),
        report.getName().getValue(),
        report.getDescription().getValue(),
        report.getLaunchDate().getValue(),
        report.getDuration().getValue(),
        report.getCapacitiesCount().getValue(),
        report.getTechnologiesCount().getValue(),
        report.getPeopleEnrolledCount().getValue()
    );
  }
}


