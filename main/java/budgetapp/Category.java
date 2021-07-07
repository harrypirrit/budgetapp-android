package budgetapp;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Category {
	/** fields */
	public String name; 
	public BigDecimal dollarTotal;
	public ArrayList<Item> items = new ArrayList<Item>();
	public int size = items.size();

	/** Constructor */
	public Category(String n){
		this.name = n;
	}

	/** toString method */
	public String toString(){
		return ""+this.name;
	}
	
}
