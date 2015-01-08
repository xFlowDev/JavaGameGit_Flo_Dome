package de.domedev.inventar;

public class Item {

	// Struktur hat Platz f�r 128 Items 
	private String[][] ccItem = new String[128][24];
	
	public Item(){
 				
	}
	
	
	public int AutoIncrement() {
		int i;
		for(i = 0; i < ccItem.length; i++){
			if(ccItem[i][0] == null){
				break;
			}	
		}
		return i;
	}


	public void makeItem(int xIndex, String xItemName, String xItemType, String xItemSpriteSheet, String xItemMinLevel, String xItemArmor, String xItemMinDmg, String xItemMaxDmg){
		ccItem[xIndex][0] = xItemName;
		ccItem[xIndex][1] = xItemType;
		ccItem[xIndex][2] = xItemMinLevel;
		ccItem[xIndex][3] = xItemArmor;	
		ccItem[xIndex][4] = xItemMinDmg;		
		ccItem[xIndex][5] = xItemMaxDmg;
	}
	
	
	public String getItemName(int xIndex){
		return ccItem[xIndex][0];
	}
	
	/* ABC[] objects = new ABC[100];
	   for (int n=0;n<100;n++) {
	      ABC abc = new ABC();
	      objects[n]= abc;
	   }
	   */
}
