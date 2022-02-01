package budgetapp;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Category {
	/** fields */
	public String name; 
	public BigDecimal dollarTotal;
	public int size;

	/** Constructor */
	public Category(String n){
		this.name = n;
		this.dollarTotal = BigDecimal.valueOf(0);
		this.size = 0;
	}

	/** toString method */
	public String toString() {
		return ""+this.name;
	}
	
}
