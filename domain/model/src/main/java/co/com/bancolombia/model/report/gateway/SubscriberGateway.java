package co.com.bancolombia.model.report.gateway;

import co.com.bancolombia.model.report.message.CreateBootcampMessage;
import co.com.bancolombia.model.report.message.UpdateBootcampMessage;

public interface SubscriberGateway {
  void save(CreateBootcampMessage message);
  void update(UpdateBootcampMessage message);
}
