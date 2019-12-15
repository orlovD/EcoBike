package ecobike;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DriverClass {
	
	StringBuilder FILELOC = null;	
	Scanner sc = new Scanner(System.in);
	
	private List<Bike> bikes = new ArrayList<Bike>();
	
	void addFoldingBike(String line) {
		String type = "FOLDING BIKE";
		String[] data = line.substring(type.length() + 1).split("; ");
		String brand = data[0];
		int wheelSize = Integer.parseInt(data[1]);
		int gearNum = Integer.parseInt(data[2]);
		int weight = Integer.parseInt(data[3]); 
		boolean hasLights = Boolean.parseBoolean(data[4]);
		String color = data[5];
		int price = Integer.parseInt(data[6]); 
		bikes.add(new FoldingBike(type, brand, wheelSize, gearNum, weight, hasLights, color, price));
	}
	
	void addEBike(String line, String type) {
		String[] data = line.substring(type.length() + 1).split("; ");
		String brand = data[0];
		int maxSpeed = Integer.parseInt(data[1]);
		int weight = Integer.parseInt(data[2]); 
		boolean hasLights = Boolean.parseBoolean(data[3]);
		int batCapacity = Integer.parseInt(data[4]);
		String color = data[5];
		int price = Integer.parseInt(data[6]); 
		
		if (type.equals("E-BIKE")) {
			bikes.add(new ElectricBike(type, brand, maxSpeed, weight, hasLights, batCapacity, color, price));
		}
		else {
			bikes.add(new SpeedElec(type, brand, maxSpeed, weight, hasLights, batCapacity, color, price));
		}
	}
	
	void addSpeedElec(String line) {
		addEBike(line, "SPEEDELEC");
	}
	
	void addElectricBike(String line) {
		addEBike(line, "E-BIKE");
	}
	
	void readFromFile(String fileLoc) throws FileNotFoundException{			
		//read data from file
		File file = new File(fileLoc.toString());		 
		Scanner sc = new Scanner(file); 	    
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String bikeType = line.substring(0, line.indexOf(' '));
			switch(bikeType) {
				case "FOLDING":
					addFoldingBike(line);
					break;
				case "E-BIKE":
					addElectricBike(line);
					break;
				case "SPEEDELEC":
					addSpeedElec(line);
					break;
			}		
		}
		sc.close();
	}
	
	void showCatalog() {
		for (Bike b : bikes) {
			System.out.println(b.toString());		
		}	
	}
	
	void addFoldingBike() {
		int wheelSize = -1;
		int gearNum = -1;
		int weight = -1;
		int price = -1;
		//get input from user
		System.out.println("Enter the brand:");
		String brand = sc.nextLine();
		System.out.println("Enter the size of the wheels (in inches):");
		while(wheelSize == -1) {
			try {
				wheelSize = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
			}
		}
		System.out.println("Enter the number of gears:");
		while(gearNum == -1) {
			try {
				gearNum = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
			}
		}
		System.out.println("Enter the weight of the bike (in grams):");
		while(weight == -1) {
			try {
				weight = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
			}
		}
		System.out.println("Enter the availability of lights at front and back (true/false):");
		boolean hasLights = Boolean.parseBoolean(sc.nextLine());
		System.out.println("Enter the color");		
		String color = sc.nextLine();
		System.out.println("Enter the price");
		while(price == -1) {
			try {
				price = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
			}
		}	
		FoldingBike fBike = new FoldingBike("FOLDING BIKE", brand, wheelSize, gearNum, weight, hasLights, color, price);
		bikes.add(fBike);
		System.out.println("Added new FOLDING BIKE:\n" + fBike.toString());
	}
	
	void addEBike(String type) {
		int maxSpeed = -1;
		int weight = -1;
		int price = -1;
		//get input from user
		System.out.println("Enter the brand:");
		String brand = sc.nextLine();
		System.out.println("Enter the maximum speed (in km/h):");
		while(maxSpeed == -1) {
			try {
				maxSpeed = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
			}
		}
		System.out.println("Enter the weight of the bike (in grams):");
		while(weight == -1) {
			try {
				weight = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
			}
		}
		System.out.println("Enter the availability of lights at front and back (true/false):");
		boolean hasLights = Boolean.parseBoolean(sc.nextLine());
		System.out.println("Enter the battery capacity (in mAh):");
		int batCapacity = Integer.parseInt(sc.nextLine());
		System.out.println("Enter the color");		
		String color = sc.nextLine();
		System.out.println("Enter the price");
		while(price == -1) {
			try {
				price = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
			}
		}
		if (type.equals("E-BIKE")) {
			ElectricBike eBike = new ElectricBike("E-BIKE", brand, maxSpeed, weight, hasLights, batCapacity, color, price);
			bikes.add(eBike);
			System.out.println("Added new E-BIKE:\n" + eBike.toString());
		}
		else {
			SpeedElec sBike = new SpeedElec("SPEEDELEC", brand, maxSpeed, weight, hasLights, batCapacity, color, price);
			bikes.add(sBike);
			System.out.println("Added new SPEEDELEC:\n" + sBike.toString());
		}	
	}
	
	void addSpeedElec() {
		addEBike("SPEEDELEC");
	}
	
	void addElectricBike() {
		addEBike("E-BIKE");
	}
	
	void writeDataToFile() throws IOException {
		FileWriter fw = new FileWriter(FILELOC.toString());	
		System.out.println("Writing the data to the file...");
		for (Bike b : bikes) {
			fw.write(b.convertIntoWritableForm() + '\n');		
		}	
		fw.close();
	}
	
	Bike findFirstItem(String brand) {
		System.out.println("Looking for brand \"" + brand + "\" in the list...");
		//dummy element for the brand lookup
		Bike fBike = new FoldingBike("", brand, 0, 0, 0, false, "", 0);
		Collections.sort(bikes);
		int found = -1;
		if ((found = Collections.binarySearch(bikes, fBike)) > -1) {
			System.out.println("First item of the given brand is:\n" + bikes.get(found).toString());
			return bikes.get(found);
		}
		else {
			System.out.println("No items of the given brand are found!");
		}
		return null;
	}
	
	public static void main(String[] args) {
		DriverClass driver = new DriverClass();
		
		//1 command line argument provided - consider it a location of the file
		if (args.length == 1) {
			driver.FILELOC = new StringBuilder(args[0]);
		}
		//0 or more than 1 command line arguments provided - consider it an error and exit
		else {
			System.out.println("Wrong amount of arguments supplied! The program will exit now!");
			System.exit(1);
		}

		try {
			driver.readFromFile(driver.FILELOC.toString());
		}
		catch(FileNotFoundException e) {
			System.out.print("Failed to read file at " + e.getMessage());
		}
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			// show options on screen
			System.out.println("\r\nPlease make your choice:\r\n" + 
					"1 - Show the entire EcoBike catalog\r\n" + 
					"2 - Add a new folding bike\r\n" + 
					"3 - Add a new speedelec\r\n" + 
					"4 - Add a new e-bike\r\n" + 
					"5 - Find the first item of a particular brand\r\n" +
					"6 - Write to file\r\n" + 
					"7 - Stop the program");		
			// get input from user
			int choise = 0;
			try {
				choise = Integer.parseInt(sc.nextLine());
			} catch(Exception e) {
				System.out.println("Wrong parameter type! Please enter numeric value!");
				continue;
				}
			switch(choise) {
				case 1:
					driver.showCatalog();
					break;
				case 2: {
						driver.addFoldingBike();			
					}
					break;
				case 3:
					driver.addSpeedElec();
					break;
				case 4:
					driver.addElectricBike();
					break;
				case 5: {
						System.out.println("Enter the brand for lookup:");
						String brand = sc.nextLine();
						driver.findFirstItem(brand);
					}
					break;
				case 6: {				
						try {
							driver.writeDataToFile();
						}
						catch (IOException e) {
							System.out.println("Can not write data to file! " + e.getMessage());
						}
					}
					break;
				case 7:
					System.out.println("The program will now exit...");
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Wrong option entered! Try again!");
					break;				
			}
		}		
	}
}

