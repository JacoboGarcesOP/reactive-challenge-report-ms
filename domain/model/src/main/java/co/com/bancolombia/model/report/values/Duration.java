package co.com.bancolombia.model.report.values;

import co.com.bancolombia.model.report.exception.DomainException;

public class Duration {
  private static final String NULL_DURATION_ERROR_MESSAGE = "The duration cannot be null.";
  private static final String INVALID_DURATION_ERROR_MESSAGE = "The duration must be greater than 0.";
  private static final String MAX_DURATION_ERROR_MESSAGE = "The duration cannot be greater than 365 days.";

  private final Integer value;

  public Duration(final Integer value) {
    if (value == null) {
      throw new DomainException(NULL_DURATION_ERROR_MESSAGE);
    }

    if (value <= 0) {
      throw new DomainException(INVALID_DURATION_ERROR_MESSAGE);
    }

    if (value > 365) {
      throw new DomainException(MAX_DURATION_ERROR_MESSAGE);
    }

    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
