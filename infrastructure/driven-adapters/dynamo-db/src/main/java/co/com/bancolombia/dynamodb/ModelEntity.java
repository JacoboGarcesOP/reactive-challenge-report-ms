package co.com.bancolombia.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import java.time.Instant;

@DynamoDbBean
public class ModelEntity {

    private Long bootcampId;
    private String name;
    private String description;
    private Instant launchDate;
    private Integer duration;
    private Integer capacitiesCount;
    private Integer technologiesCount;
    private Integer peopleEnrolledCount;

    public ModelEntity() {
    }

    public ModelEntity(Long bootcampId, String name, String description, Instant launchDate, 
                      Integer duration, Integer capacitiesCount, Integer technologiesCount, 
                      Integer peopleEnrolledCount) {
        this.bootcampId = bootcampId;
        this.name = name;
        this.description = description;
        this.launchDate = launchDate;
        this.duration = duration;
        this.capacitiesCount = capacitiesCount;
        this.technologiesCount = technologiesCount;
        this.peopleEnrolledCount = peopleEnrolledCount;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("bootcampId")
    public Long getBootcampId() {
        return bootcampId;
    }

    public void setBootcampId(Long bootcampId) {
        this.bootcampId = bootcampId;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDbAttribute("launchDate")
    public Instant getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Instant launchDate) {
        this.launchDate = launchDate;
    }

    @DynamoDbAttribute("duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @DynamoDbAttribute("capacitiesCount")
    public Integer getCapacitiesCount() {
        return capacitiesCount;
    }

    public void setCapacitiesCount(Integer capacitiesCount) {
        this.capacitiesCount = capacitiesCount;
    }

    @DynamoDbAttribute("technologiesCount")
    public Integer getTechnologiesCount() {
        return technologiesCount;
    }

    public void setTechnologiesCount(Integer technologiesCount) {
        this.technologiesCount = technologiesCount;
    }

    @DynamoDbAttribute("peopleEnrolledCount")
    public Integer getPeopleEnrolledCount() {
        return peopleEnrolledCount;
    }

    public void setPeopleEnrolledCount(Integer peopleEnrolledCount) {
        this.peopleEnrolledCount = peopleEnrolledCount;
    }
}
