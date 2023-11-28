package main.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;

public class StripeService {

	public StripeService(String apiKey) {
		// Set your Stripe secret key
		Stripe.apiKey = apiKey;
	}

	public PaymentIntent createPaymentIntent(Long amount, String currency) throws StripeException {
		PaymentIntentCreateParams params = PaymentIntentCreateParams.builder().setAmount(amount).setCurrency(currency)
				.build();
		return PaymentIntent.create(params);
	}

	public Refund createRefund(String paymentIntentId) throws StripeException {
		RefundCreateParams params = RefundCreateParams.builder().setPaymentIntent(paymentIntentId).build();
		return Refund.create(params);
	}

	public PaymentIntent getPaymentIntent(String paymentIntentId) throws StripeException {
		return PaymentIntent.retrieve(paymentIntentId);
	}

	// Method to get payment status
	public String getPaymentStatus(String paymentIntentId) throws StripeException {
		if (paymentIntentId == null || paymentIntentId.isEmpty()) {
			throw new IllegalArgumentException("Invalid Payment Intent ID");
		}

		PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
		// The status can be "succeeded", "pending", "canceled", etc.
		return paymentIntent.getStatus();
	}

	public static void main(String[] args) {
		// Replace with your Stripe secret key
		String apiKey = "sk_test_51OBWkqC7WQVamEmls7GxOehlkLYrpnf8wmO4GTFGaExIuGTsHgnZkjI14gAX6VYghTn1RkmMbH82CfSvjwrGHvbe00TG7MNeYH";
		StripeService stripeService = new StripeService(apiKey);

		// Example PaymentIntent ID
		String paymentIntentId = "pi_1234567890";

		try {
			String status = stripeService.getPaymentStatus(paymentIntentId);
			System.out.println("Payment Status: " + status);
		} catch (StripeException e) {
			System.out.println("Stripe API error: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
