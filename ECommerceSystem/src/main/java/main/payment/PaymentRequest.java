package main.payment;

public class PaymentRequest {

	private Long amount; // in smallest currency unit (cents for CAD)
	private String currency;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
