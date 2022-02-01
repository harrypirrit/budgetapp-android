package budgetapp;

import java.util.ArrayList;
import java.util.Random;

public class UserIterator {
	//fields
	static Random R = new Random();

	/**
	 * Creates a List of Categories based on the categories param.
	 * @return Array of Category class, based on categories param.
	 */
	public static Category[] initCategories(String[] categories){
		Category[] categoryList = new Category[categories.length];
		for (int i = 0; i < categoryList.length; i++){
			categoryList[i] = new Category(categories[i]);
		}
		return categoryList;
	}

	/**
	 * Iterates through the itemlist and sets each Item's category to user input.
	 * @param itemList
	 * @param categoryList
	 * @return
	 */
	public static Item[] categorizeItems(ArrayList<Item> itemList, Category[] categoryList, String[] categories){
		Item[] itemArray;

		for (Item item : itemList){
			int rInt = R.nextInt(categoryList.length);
			
			//random testing data mocking user input
			String categoryInput = categories[rInt];

			for (Category cat : categoryList){
				if (categoryInput.equals(cat.name)){ item.category = cat;
				}
			}

		}		
		return itemList.toArray(new Item[0]);
	}
}
