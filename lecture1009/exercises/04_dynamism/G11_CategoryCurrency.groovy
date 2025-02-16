class Money {
    Integer amount
    String currency

    Money plus(Money other) {
        if (this.currency != other.currency) throw new IllegalArgumentException('Cannot add different currencies');
        new Money(amount: this.amount + other.amount, currency: this.currency)
    }

    public String toString() {
        "${amount} ${currency}"
    }
}

class MoneyCategory {
    static Money getEur(Integer i) {
        return new Money(amount: i, currency: "EUR")
    }
    
    static Money getUsd(Integer i) {
        return new Money(amount: i, currency: "USD")
    }
}

use(MoneyCategory) {
    println 10.eur
    println 10.eur + 20.eur
    println 10.usd + 20.usd
}