package budgetapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class UserIterator {
	/**
	 * Creates a HashMap of Categories based on the categories param.
	 * @return HashMap of Category class, based on categories param.
	 */
	public static HashMap<String, Category> initCategories(String[] categories){
		HashMap<String, Category> categoryHashMap = new HashMap<String, Category>();
		for (String category : categories){
			categoryHashMap.put(category, new Category(category));
		}

		return categoryHashMap;
	}

}
