import java.util.Random;


public class FantasyNameGenerator {

	public static void main(String[] args) {
		

	}
	

	public String name;
	public String place;
	public String animal;
	
	
	public FantasyNameGenerator(String aName, String aPlace, String anAnimal) {
		name = aName.trim().toLowerCase();
		place = aPlace.trim().toLowerCase();
		animal = anAnimal.trim().toLowerCase();
	}
	
	public int ran3() 
	{
		Random aRandomNumber = new Random();
		return aRandomNumber.nextInt(3) + 1;
	}

	public String generateNameSubstring()
	{
		return name.substring(0, ran3());
	}

	public String generatePlaceSubstring()
	{
		return place.substring(0, ran3());
	}

	public String generateAnimalSubstring()
	{
		return animal.substring(0, ran3());
	}
	
	public String generateFantasyName()
	{
		return generateNameSubstring() + generatePlaceSubstring() + generateAnimalSubstring();
	}
	
	public void printName()
	{
		System.out.println(generateFantasyName());
	}
	
	public String generateFantasyName2()
	{
		Random x = new Random();
		int y = x.nextInt(6);
		
		switch(y) {
			case 0: return generateNameSubstring() + generatePlaceSubstring() + generateAnimalSubstring();
			
			case 1: return generatePlaceSubstring() + generateNameSubstring() + generateAnimalSubstring();
			
			case 2: return generatePlaceSubstring() + generateAnimalSubstring() + generateNameSubstring();
			
			case 3: return generateNameSubstring() + generateAnimalSubstring() + generatePlaceSubstring();
			
			case 4: return generateAnimalSubstring() + generateNameSubstring() + generatePlaceSubstring();
			
			default: return generateAnimalSubstring() + generatePlaceSubstring() + generateNameSubstring();
			
		}
	}
	
	public String capitalise(String x)
	{
		return x.substring(0,1).toUpperCase() + x.toLowerCase().substring(1);
		
	}
	
	public void printName2()
	{
		String temp = generateFantasyName2();
		String temp2 = capitalise(temp);
		System.out.println(temp2);
	}
	
}
