package co.com.bancolombia.model.report.gateway;

import co.com.bancolombia.model.report.Report;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface ReportGateway {
  Mono<Report> save(Report report);
  Mono<Report> update(Report report);
  Mono<Report> findById(Long bootcampId);
  Flux<Report> findAll();
}