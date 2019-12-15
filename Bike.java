package ecobike;

import java.util.Objects;

public abstract class Bike implements Comparable<Bike> {
	String type;
	String brand;
	int weight;
	boolean hasLights;
	String color;
	int price;
	
	//c'tor
	Bike(String type, String brand, int weight, boolean hasLights, String color, int price) {
		this.type = type;
		this.brand = brand;
		this.weight = weight;
		this.hasLights = hasLights;
		this.color = color;
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {	
		if(obj == null) {
			return false;
		}		
		if (!obj.getClass().isAssignableFrom(getClass())) {
            return false;
        }
		if (obj == this) {
			return true;
		}
		Bike bike = (Bike) obj;
		if (this.brand.compareTo(bike.brand) != 0 || (this.hasLights != bike.hasLights) 
				|| this.color.compareTo(bike.color) != 0 || this.price != bike.price) {
			return false;		
		}	
		return true;		
	}

	@Override
	public int hashCode() {
		 return Objects.hash(type, brand, hasLights, color, price);
	}
	
	@Override
	public String toString() {
		StringBuilder data = new StringBuilder(this.type);
		data.append(" ");
		data.append(this.brand);
		data.append(";");
		if (this.hasLights) {
			data.append(" and head/tail light.");
		}
		else {
			data.append(" and no head/tail light.");
		}		
		return data.append("\nPrice: " + this.price + " euros.").toString();		
	}
	
	public abstract String convertIntoWritableForm();
	
	public String convertIntoWritableFormStart() {
		StringBuilder bikeInfo = new StringBuilder(this.type);
		bikeInfo.append(" ");
		bikeInfo.append(brand);
		bikeInfo.append("; ");
		return bikeInfo.toString(); 
	}
	
	public String convertIntoWritableFormEnd(String str) {
		StringBuilder bikeInfo = new StringBuilder();
		bikeInfo.append(weight);
		bikeInfo.append("; ");
		bikeInfo.append(hasLights);
		bikeInfo.append("; ");
		bikeInfo.append(str);
		bikeInfo.append(color);
		bikeInfo.append("; ");
		bikeInfo.append(price);
		return bikeInfo.toString(); 
	}
	
	public int compareTo(Bike b) {
		return brand.compareTo(b.brand);
	}
}
