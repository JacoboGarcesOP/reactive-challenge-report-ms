package co.com.bancolombia.model.report.values;

import co.com.bancolombia.model.report.exception.DomainException;

public class Count {
  private static final String NULL_COUNT_ERROR_MESSAGE = "The count cannot be null.";
  private static final String INVALID_COUNT_ERROR_MESSAGE = "The count must be greater than or equal to 0.";

  private final Integer value;

  public Count(final Integer value) {
    if (value == null) {
      throw new DomainException(NULL_COUNT_ERROR_MESSAGE);
    }

    if (value < 0) {
      throw new DomainException(INVALID_COUNT_ERROR_MESSAGE);
    }

    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
