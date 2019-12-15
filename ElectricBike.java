package ecobike;

import java.util.Objects;

public class ElectricBike extends Bike {
	int maxSpeed;
	int batCapacity;
	
	//c'tor
	ElectricBike(String type, String brand, int maxSpeed, int weight, boolean hasLights, int batCapacity, String color, int price) {
		super(type, brand, weight, hasLights, color, price);
		this.maxSpeed = maxSpeed;
		this.batCapacity = batCapacity;
	}
	
	@Override
	public boolean equals(Object obj) {
		//check parent method
		if (!super.equals(obj)) {
			return false;
		}
		ElectricBike bike = (ElectricBike) obj;
		if (this.maxSpeed != bike.maxSpeed || this.batCapacity != bike.batCapacity) {
			return false;		
		}	
		return true;		
	}

	@Override
	public int hashCode() {
		 return super.hashCode() + Objects.hash(maxSpeed, batCapacity);
	}
	
	@Override
	public String toString() {
		String pData = super.toString();
		StringBuilder data = new StringBuilder(pData.substring(0, pData.indexOf(';')));
		data.append(" with " + this.batCapacity + " mAh battery");
		data.append(pData.substring(pData.indexOf(';') + 1));
		return data.toString();		
	}
	
	public String convertIntoWritableForm() {
		StringBuilder bikeInfo = new StringBuilder(super.convertIntoWritableFormStart());
		bikeInfo.append(maxSpeed);
		bikeInfo.append("; ");
		bikeInfo.append(super.convertIntoWritableFormEnd(Integer.toString(batCapacity) + "; "));
		return bikeInfo.toString(); 	
	}
}
