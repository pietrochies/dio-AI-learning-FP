package dio.budgeting.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Transaction {
    private TransactionId id;
    private String description;
    private long amount;
    private Category category;

    public Transaction(String description, long amount, Category category) {
        this(new TransactionId(), description, amount, category);
    }

    public Transaction(TransactionId id, String description, long amount, Category category) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Transaction description must not be blank");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }

        this.id = Objects.requireNonNull(id, "Transaction id must not be null");
        this.description = description;
        this.amount = amount;
        this.category = Objects.requireNonNull(category, "Transaction category must not be null");
    }
}
