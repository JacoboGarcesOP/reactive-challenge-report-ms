package co.com.bancolombia.events;

import co.com.bancolombia.model.report.message.CreateBootcampMessage;
import co.com.bancolombia.model.report.message.UpdateBootcampMessage;
import co.com.bancolombia.model.report.gateway.SubscriberGateway;
import co.com.bancolombia.usecase.CreateReportUseCase;
import co.com.bancolombia.usecase.UpdateReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportEventListener implements SubscriberGateway {

  private final CreateReportUseCase createReportUseCase;
  private final UpdateReportUseCase updateReportUseCase;

  @Override
  @RabbitListener(queues = "${adapter.rabbit.create.queue}")
  public void save(CreateBootcampMessage report) {
    log.info("Received message to create report with bootcampId: {}", report.getBootcampId());
    createReportUseCase.create(report)
      .doOnSuccess(savedReport -> log.info("Report created successfully with bootcampId: {}",
        savedReport.getBootcampId().getValue()))
      .doOnError(error -> log.error("Error creating report: {}", error.getMessage()))
      .block();
  }

  @Override
  @RabbitListener(queues = "${adapter.rabbit.update.queue}")
  public void update(UpdateBootcampMessage report) {
    log.info("Received message to update report with bootcampId: {}", report.getBootcampId());
    updateReportUseCase.update(report)
      .doOnSuccess(updatedReport -> log.info("Report updated successfully with bootcampId: {}",
        updatedReport.getBootcampId().getValue()))
      .doOnError(error -> log.error("Error updating report: {}", error.getMessage()))
      .block();
  }
}
