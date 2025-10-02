package co.com.bancolombia.model.report.values;

import co.com.bancolombia.model.report.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void shouldThrowWhenNameIsNullOrEmpty() {
        assertThrows(DomainException.class, () -> new Name(null));
        assertThrows(DomainException.class, () -> new Name("   "));
    }

    @Test
    void shouldThrowWhenNameExceedsMaxLength() {
        String longName = "a".repeat(51);
        assertThrows(DomainException.class, () -> new Name(longName));
    }

    @Test
    void shouldTrimAndStoreValidName() {
        Name name = new Name("  Bootcamp  ");
        assertEquals("Bootcamp", name.getValue());
    }
}


