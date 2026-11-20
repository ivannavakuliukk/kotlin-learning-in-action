package kotlin_in_action.section_9_operators_and_conventions.overloading_arithmetic_operators;

public class OperatorExampleMoney {
    private final int amount;

    public OperatorExampleMoney(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public OperatorExampleMoney plus(OperatorExampleMoney other) {
        return new OperatorExampleMoney(this.amount + other.amount);
    }

    public OperatorExampleMoney minus(OperatorExampleMoney other) {
        return new OperatorExampleMoney(this.amount - other.amount);
    }
}
