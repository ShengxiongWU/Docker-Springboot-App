package main.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.CardException;

public class PaymentService {

	private StripeService stripeService;

	public PaymentService(String apiKey) {
		this.stripeService = new StripeService(apiKey);
	}

	// Method to process payment
	public PaymentResponse processPayment(PaymentRequest request) {
		// Implement logic to process payment using Stripe
		PaymentResponse response = new PaymentResponse();
		try {
			// Convert PaymentRequest to Stripe PaymentIntentCreateParams
			PaymentIntentCreateParams params = PaymentIntentCreateParams.builder().setAmount(request.getAmount()) // in
																													// cents
					.setCurrency(request.getCurrency()).build();
			PaymentIntent paymentIntent = PaymentIntent.create(params);
			response.setTransactionId(paymentIntent.getId());
			response.setStatus(paymentIntent.getStatus());
		} catch (AuthenticationException e) {
			response.setMessage("Authentication with Stripe API failed: " + e.getMessage());
		} catch (InvalidRequestException e) {
			response.setMessage("Invalid request to Stripe API: " + e.getMessage());
		} catch (ApiConnectionException e) {
			response.setMessage("Network error connecting to Stripe: " + e.getMessage());
		} catch (CardException e) {
			response.setMessage("Card error occurred: " + e.getMessage());
		} catch (StripeException e) {
			response.setMessage("Stripe API error: " + e.getMessage());
		} catch (Exception e) {
			response.setMessage("General error in payment processing: " + e.getMessage());
		}
		return response;
	}

	// Method to handle payment status
	public PaymentStatus getPaymentStatus(String transactionId) {
		// Implement logic to check payment status using Stripe
		PaymentStatus status = new PaymentStatus();
		try {
			PaymentIntent paymentIntent = stripeService.getPaymentIntent(transactionId);
			status.setStatus(paymentIntent.getStatus());
		} catch (StripeException e) {
			status.setMessage("Error fetching payment status: " + e.getMessage());
		}
		return status;
	}

	// Method to handle refunds
	public RefundResponse processRefund(RefundRequest request) {
		// Implement logic for processing refunds using Stripe
		RefundResponse response = new RefundResponse();
		try {
			RefundCreateParams params = RefundCreateParams.builder().setPaymentIntent(request.getTransactionId())
					.build();
			Refund refund = Refund.create(params);
			response.setRefundId(refund.getId());
			response.setStatus(refund.getStatus());
		} catch (StripeException e) {
			response.setMessage("Error processing refund: " + e.getMessage());
		}
		return response;
	}

	// Additional methods for other payment functionalities
}
