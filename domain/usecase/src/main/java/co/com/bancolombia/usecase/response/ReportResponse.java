package co.com.bancolombia.usecase.response;

import java.util.Date;

public class ReportResponse {
  private final Long bootcampId;
  private final String name;
  private final String description;
  private final Date launchDate;
  private final Integer duration;
  private final Integer capacitiesCount;
  private final Integer technologiesCount;
  private final Integer peopleEnrolledCount;

  public ReportResponse(Long bootcampId, String name, String description,
                        Date launchDate, Integer duration, Integer capacitiesCount,
                        Integer technologiesCount, Integer peopleEnrolledCount) {
    this.bootcampId = bootcampId;
    this.name = name;
    this.description = description;
    this.launchDate = launchDate;
    this.duration = duration;
    this.capacitiesCount = capacitiesCount;
    this.technologiesCount = technologiesCount;
    this.peopleEnrolledCount = peopleEnrolledCount;
  }

  public Long getBootcampId() {
    return bootcampId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public Integer getDuration() {
    return duration;
  }

  public Integer getCapacitiesCount() {
    return capacitiesCount;
  }

  public Integer getTechnologiesCount() {
    return technologiesCount;
  }

  public Integer getPeopleEnrolledCount() {
    return peopleEnrolledCount;
  }
}



