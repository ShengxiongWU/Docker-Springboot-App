package main.payment;

public class RefundRequest {
	private String transactionId;
	private Long amount; // Optional, depends on whether you want partial refunds

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
