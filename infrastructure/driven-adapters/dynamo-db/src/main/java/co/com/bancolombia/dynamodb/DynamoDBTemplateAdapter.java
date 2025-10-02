package co.com.bancolombia.dynamodb;

import co.com.bancolombia.model.report.Report;
import co.com.bancolombia.model.report.gateway.ReportGateway;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.Date;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

@Repository
@RequiredArgsConstructor
public class DynamoDBTemplateAdapter implements ReportGateway {

  private final DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;
  
  @Value("${aws.dynamodb.table-name}")
  private String tableName;

  @Override
  public Mono<Report> save(Report report) {
    DynamoDbAsyncTable<ModelEntity> table = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(ModelEntity.class));
    
    ModelEntity entity = convertToEntity(report);
    
    return Mono.fromFuture(
        table.putItem(entity)
            .thenApply(unused -> report)
    );
  }

  @Override
  public Mono<Report> update(Report report) {
    DynamoDbAsyncTable<ModelEntity> table = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(ModelEntity.class));
    
    ModelEntity entity = convertToEntity(report);
    
    return Mono.fromFuture(
        table.putItem(entity)
            .thenApply(unused -> report)
    );
  }

  @Override
  public Mono<Report> findById(Long bootcampId) {
    DynamoDbAsyncTable<ModelEntity> table = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(ModelEntity.class));

    Key key = Key.builder()
        .partitionValue(bootcampId)
        .build();

    return Mono.fromFuture(table.getItem(key))
        .flatMap(item -> item == null
            ? Mono.empty()
            : Mono.just(convertToReport(item)));
  }

  @Override
  public Flux<Report> findAll() {
    DynamoDbAsyncTable<ModelEntity> table = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(ModelEntity.class));
    return Flux.from(table.scan(ScanEnhancedRequest.builder().build()).items())
        .map(this::convertToReport);
  }

  private ModelEntity convertToEntity(Report report) {
    return new ModelEntity(
        report.getBootcampId().getValue(),
        report.getName().getValue(),
        report.getDescription().getValue(),
        convertDateToInstant(report.getLaunchDate().getValue()),
        report.getDuration().getValue(),
        report.getCapacitiesCount().getValue(),
        report.getTechnologiesCount().getValue(),
        report.getPeopleEnrolledCount().getValue()
    );
  }

  private Report convertToReport(ModelEntity entity) {
    return new Report(
        entity.getBootcampId(),
        entity.getName(),
        entity.getDescription(),
        convertInstantToDate(entity.getLaunchDate()),
        entity.getDuration(),
        entity.getCapacitiesCount(),
        entity.getTechnologiesCount(),
        entity.getPeopleEnrolledCount()
    );
  }

  private Instant convertDateToInstant(Date date) {
    return date == null ? null : date.toInstant();
  }

  private Date convertInstantToDate(Instant instant) {
    return instant == null ? null : Date.from(instant);
  }
}
