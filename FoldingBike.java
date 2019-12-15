package ecobike;

import java.util.Objects;

public class FoldingBike extends Bike {
	int wheelSize;
	int numGears;
	
	//c'tor
	FoldingBike(String type, String brand, int wheelSize, int numGears, int weight, boolean hasLights, String color, int price) {
		super(type, brand, weight, hasLights, color, price);
		this.wheelSize = wheelSize;
		this.numGears = numGears;
	}
	
	@Override
	public boolean equals(Object obj) {	
		//check parent method
		if (!super.equals(obj)) {
			return false;
		}	
		FoldingBike bike = (FoldingBike) obj;
		if (this.wheelSize != bike.wheelSize || this.numGears != bike.numGears) {
			return false;		
		}	
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + Objects.hash(wheelSize, numGears);
	}
	
	@Override
	public String toString() {
		String pData = super.toString();
		StringBuilder data = new StringBuilder(pData.substring(0, pData.indexOf(';')));
		data.append(" with " + this.numGears + " gear(s)");
		data.append(pData.substring(pData.indexOf(';') + 1));
		return data.toString();		
	}
	
	public String convertIntoWritableForm() {
		StringBuilder bikeInfo = new StringBuilder(super.convertIntoWritableFormStart());
		bikeInfo.append(wheelSize);
		bikeInfo.append("; ");
		bikeInfo.append(numGears);
		bikeInfo.append("; ");
		bikeInfo.append(super.convertIntoWritableFormEnd(""));
		return bikeInfo.toString();
	}
}
