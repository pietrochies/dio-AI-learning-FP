package dio.budgeting.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {
    @Test
    void shouldCreateValidTransaction() {
        assertDoesNotThrow(() -> new Transaction("Supermercado", 1250, Category.GROCERIES));
    }

    @Test
    void shouldRejectBlankDescription() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("  ", 1250, Category.GROCERIES));
    }

    @Test
    void shouldRejectNonPositiveAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("Supermercado", 0, Category.GROCERIES));
    }

    @Test
    void shouldRejectNullCategory() {
        assertThrows(NullPointerException.class,
                () -> new Transaction("Supermercado", 1250, null));
    }
}
