package co.com.bancolombia.model.report;

import co.com.bancolombia.model.report.values.*;
import java.util.Date;

public class Report {
  private final BootcampId bootcampId;
  private final Name name;
  private final Description description;
  private final LaunchDate launchDate;
  private final Duration duration;
  private final Count capacitiesCount;
  private final Count technologiesCount;
  private final Count peopleEnrolledCount;

  public Report(Long bootcampId, String name, String description, 
                Date launchDate, Integer duration, Integer capacitiesCount,
                Integer technologiesCount, Integer peopleEnrolledCount) {
    this.bootcampId = new BootcampId(bootcampId);
    this.name = new Name(name);
    this.description = new Description(description);
    this.launchDate = new LaunchDate(launchDate);
    this.duration = new Duration(duration);
    this.capacitiesCount = new Count(capacitiesCount);
    this.technologiesCount = new Count(technologiesCount);
    this.peopleEnrolledCount = new Count(peopleEnrolledCount);
  }

  public BootcampId getBootcampId() {
    return bootcampId;
  }

  public Name getName() {
    return name;
  }

  public Description getDescription() {
    return description;
  }

  public LaunchDate getLaunchDate() {
    return launchDate;
  }

  public Duration getDuration() {
    return duration;
  }

  public Count getCapacitiesCount() {
    return capacitiesCount;
  }

  public Count getTechnologiesCount() {
    return technologiesCount;
  }

  public Count getPeopleEnrolledCount() {
    return peopleEnrolledCount;
  }
}
