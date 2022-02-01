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
	
}
