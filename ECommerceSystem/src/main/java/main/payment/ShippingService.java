package main.payment;

import java.util.HashMap;
import java.util.Map;

public class ShippingService {

	private static final Map<String, Double> COUNTRY_RATES = new HashMap<>();
	static {
		COUNTRY_RATES.put("usa", 5.0);
		COUNTRY_RATES.put("canada", 7.0);
		// Add more countries and rates
	}

	public ShippingResponse calculateShippingCost(ShippingRequest request) {
		ShippingResponse response = new ShippingResponse();

		try {
			double baseCost = calculateBaseCost(request.getPackageWeight(), request.getPackageDimensions());
			double destinationCost = getRateForCountry(request.getDestinationCountry());
			double speedCost = calculateSpeedCost(request.getShippingSpeed());

			double totalCost = baseCost + destinationCost + speedCost;
			response.setShippingCost(totalCost);
			response.setStatus("Success");
			response.setMessage("Shipping cost calculated successfully");
		} catch (Exception e) {
			response.setStatus("Error");
			response.setMessage("Error calculating shipping cost: " + e.getMessage());
		}

		return response;
	}

	private double calculateBaseCost(double weight, String dimensions) {
		// Implement logic to calculate base cost based on weight and dimensions
		// Example: base rate + weight-based rate
		double baseRate = 2.00; // Base rate in dollars
		double ratePerKilogram = 1.50; // Rate per kilogram in dollars
		return baseRate + (weight * ratePerKilogram);
	}

	private double getRateForCountry(String country) {
		return COUNTRY_RATES.getOrDefault(country.toLowerCase(), 10.0); // Default rate
	}

	private double calculateSpeedCost(String shippingSpeed) {
		// Implement logic for additional cost based on shipping speed
		switch (shippingSpeed.toLowerCase()) {
		case "standard":
			return 0.0;
		case "express":
			return 5.0; // Additional cost for express shipping
		default:
			return 0.0;
		}
	}
}