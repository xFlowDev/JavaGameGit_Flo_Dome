package de.domedev.inventar;

public class Item {

	// Struktur hat Platz für 128 Items 
	private Item[] ccItem = new Item[128];
	private String ccItemName;
	
	public Item(){
		// Item List erstellt: Erstelle jetzt Items!		
		System.out.println("Erstelle");
		makeItem(AutoIncrement(), "Name1");
	}
	
	
	private int AutoIncrement() {
		int i = 0;
		for(i = 1; i < ccItem.length; i++){
			if(ccItem[i] == null){
				break;
			}	
		}
		return i;
	}


	public void makeItem(int xIndex, String xItemName){
		//ccItem[xIndex].ccItemName = xItemName;
		//System.out.println("Erstelle Item: " + ccItem[xIndex].ccItemName);
	}
	
	
	/* ABC[] objects = new ABC[100];
	   for (int n=0;n<100;n++) {
	      ABC abc = new ABC();
	      objects[n]= abc;
	   }
	   */
}
