package co.com.bancolombia.usecase;

import co.com.bancolombia.model.report.Report;
import co.com.bancolombia.model.report.gateway.ReportGateway;
import co.com.bancolombia.model.report.message.UpdateBootcampMessage;
import co.com.bancolombia.usecase.exception.BussinessException;
import reactor.core.publisher.Mono;

public class UpdateReportUseCase {
  
  private static final String REPORT_NOT_FOUND_MESSAGE = "Report not found for bootcampId: %s";
  
  private final ReportGateway reportGateway;

  public UpdateReportUseCase(ReportGateway reportGateway) {
    this.reportGateway = reportGateway;
  }
  
  public Mono<Report> update(UpdateBootcampMessage message) {
    return reportGateway.findById(message.getBootcampId())
        .switchIfEmpty(Mono.error(new BussinessException(String.format(REPORT_NOT_FOUND_MESSAGE, message.getBootcampId()))))
        .flatMap(existingReport -> {
          Report updatedReport = updateReportWithNewCount(existingReport, message.getPeopleEnrolledCount());
          return reportGateway.update(updatedReport);
        });
  }

  private Report updateReportWithNewCount(Report existingReport, Integer newPeopleEnrolledCount) {
    return new Report(
        existingReport.getBootcampId().getValue(),
        existingReport.getName().getValue(),
        existingReport.getDescription().getValue(),
        existingReport.getLaunchDate().getValue(),
        existingReport.getDuration().getValue(),
        existingReport.getCapacitiesCount().getValue(),
        existingReport.getTechnologiesCount().getValue(),
        newPeopleEnrolledCount
    );
  }
}
