package main.payment;

public class ShippingRequest {
	private double packageWeight;
	private String packageDimensions;
	private String destinationCountry;
	private String shippingSpeed;

	// Getters and Setters
	public double getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(double packageWeight) {
		this.packageWeight = packageWeight;
	}

	public String getPackageDimensions() {
		return packageDimensions;
	}

	public void setPackageDimensions(String packageDimensions) {
		this.packageDimensions = packageDimensions;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getShippingSpeed() {
		return shippingSpeed;
	}

	public void setShippingSpeed(String shippingSpeed) {
		this.shippingSpeed = shippingSpeed;
	}
}