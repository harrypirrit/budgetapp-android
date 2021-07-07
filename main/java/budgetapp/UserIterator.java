package budgetapp;

import java.util.ArrayList;
import java.util.Random;

public class UserIterator {
	//fields
	final static String[] CATEGORY_NAMES = {"CLOTHES", "SCHOOL", "ENTERTAINMENT", "DRUGS", "FOOD", "MISC", "DEBT"};
	static Random R = new Random();

	/**
	 * Creates a List of Categories based on the CATEGORY_NAMES final variable.
	 * @return
	 */
	public static Category[] initCategories(){
		Category[] categoryList = new Category[CATEGORY_NAMES.length];
		for (int i = 0; i < categoryList.length; i++){
			categoryList[i] = new Category(CATEGORY_NAMES[i]);
		}
		return categoryList;
	}

	/**
	 * Iterates through the itemlist and sets each Item's category to user input.
	 * @param itemList
	 * @param categoryList
	 * @return
	 */
	public static ArrayList<Item> categorizeItems(ArrayList<Item> itemList, Category[] categoryList){

		for (Item item : itemList){
			int rInt = R.nextInt(categoryList.length);
			//random testing data mocking user input
			String categoryInput = CATEGORY_NAMES[rInt];

			for (Category cat : categoryList){
				if (categoryInput.equals(cat.name)){ item.category = cat;
				}
			}
		
		}		
		return itemList;
	}
}
