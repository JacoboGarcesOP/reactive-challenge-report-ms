package co.com.bancolombia.model.report.values;

import co.com.bancolombia.model.report.exception.DomainException;
import java.util.Date;

public class LaunchDate {
  private static final String NULL_LAUNCH_DATE_ERROR_MESSAGE = "The launch date cannot be null.";

  private final Date value;

  public LaunchDate(final Date value) {
    if (value == null) {
      throw new DomainException(NULL_LAUNCH_DATE_ERROR_MESSAGE);
    }

    this.value = value;
  }

  public Date getValue() {
    return value;
  }
}
