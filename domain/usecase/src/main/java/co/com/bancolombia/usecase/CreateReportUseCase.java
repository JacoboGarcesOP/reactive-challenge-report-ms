package co.com.bancolombia.usecase;

import co.com.bancolombia.model.report.Report;
import co.com.bancolombia.model.report.gateway.ReportGateway;
import co.com.bancolombia.model.report.message.CreateBootcampMessage;
import co.com.bancolombia.usecase.exception.BussinessException;
import reactor.core.publisher.Mono;

public class CreateReportUseCase {
  
  private static final String REPORT_ALREADY_EXISTS_MESSAGE = "Report already exists for bootcampId: %s";
  
  private final ReportGateway reportGateway;

  public CreateReportUseCase(ReportGateway reportGateway) {
    this.reportGateway = reportGateway;
  }
  
  public Mono<Report> create(CreateBootcampMessage message) {
    return reportGateway.findById(message.getBootcampId())
        .hasElement()
        .flatMap(exists -> {
          if (exists) {
            return Mono.error(new BussinessException(String.format(REPORT_ALREADY_EXISTS_MESSAGE, message.getBootcampId())));
          }
          return reportGateway.save(convertToReport(message));
        });
  }

  private Report convertToReport(CreateBootcampMessage createBootcampMessage) {
    return new Report(
      createBootcampMessage.getBootcampId(),
      createBootcampMessage.getName(),
      createBootcampMessage.getDescription(),
      createBootcampMessage.getLaunchDate(),
      createBootcampMessage.getDuration(),
      createBootcampMessage.getCapacitiesCount(),
      createBootcampMessage.getTechnologiesCount(),
      0
    );
  }
}
