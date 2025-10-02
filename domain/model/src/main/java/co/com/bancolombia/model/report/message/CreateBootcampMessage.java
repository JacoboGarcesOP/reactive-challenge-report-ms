package co.com.bancolombia.model.report.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBootcampMessage {
  private Long bootcampId;
  private String name;
  private String description;
  
  private Date launchDate;
  
  private Integer duration;
  private Integer capacitiesCount;
  private Integer technologiesCount;
}
