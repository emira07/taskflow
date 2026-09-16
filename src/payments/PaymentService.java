public class PaymentService {

    private final CreditCardPayment payment;

    public PaymentService() {
        // Intentionally depends directly on a concrete implementation.
        this.payment = new CreditCardPayment();
    }

    public void pay(double amount) {
        payment.process(amount);
    }
}

class CreditCardPayment {

    public void process(double amount) {
        System.out.println("Processing credit card payment: " + amount);
    }
}
