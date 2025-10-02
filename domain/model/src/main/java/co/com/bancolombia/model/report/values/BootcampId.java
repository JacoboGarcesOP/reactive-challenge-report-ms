package co.com.bancolombia.model.report.values;

import co.com.bancolombia.model.report.exception.DomainException;

public class BootcampId {
  private static final String NULL_BOOTCAMP_ID_ERROR_MESSAGE = "The bootcamp ID cannot be null.";
  private static final String INVALID_BOOTCAMP_ID_ERROR_MESSAGE = "The bootcamp ID must be greater than 0.";

  private final Long value;

  public BootcampId(final Long value) {
    if (value == null) {
      throw new DomainException(NULL_BOOTCAMP_ID_ERROR_MESSAGE);
    }

    if (value <= 0) {
      throw new DomainException(INVALID_BOOTCAMP_ID_ERROR_MESSAGE);
    }

    this.value = value;
  }

  public Long getValue() {
    return value;
  }
}
