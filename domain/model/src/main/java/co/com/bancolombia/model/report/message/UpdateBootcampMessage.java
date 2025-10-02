package co.com.bancolombia.model.report.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBootcampMessage {
  private Long bootcampId;
  private Integer peopleEnrolledCount;
}
