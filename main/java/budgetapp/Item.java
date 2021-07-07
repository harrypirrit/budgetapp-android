package budgetapp;

import java.math.BigDecimal;


public class Item{
	/** fields */
	public String date;
	public String description;
	public BigDecimal amount;
	public Category category;

	/** Constructor */
	public Item (String date, String description, BigDecimal amount ){
		this.date = date;
		this.description = description;
		this.amount = amount;
	}

	/** toString method */
	public String toString(){
		String str = "\n {Item Date : " +this.date +"\nItem Description : " +this.description +"\nItem Amount : $" +this.amount + "\nCategory : " +this.category +"\n}-- -- -- --";
		return str;
	}
}