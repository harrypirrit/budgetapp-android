package budgetapp;

import java.util.ArrayList;

public class Summary {
	final String[] CATEGORY_NAMES = new String[5];

	/**
	 * Iterates through each Item, and modifies category values.
	 * @param itemList
	 * @param categoryList
	 */
	public static void updateCategoryData(Item[] itemList, Category[] categoryList){

		for (Item item : itemList){
			for (Category cat : categoryList){
				if (item.category != null && item.category.equals(cat)){
					cat.size++;

					if(cat.dollarTotal == null){
						cat.dollarTotal = item.amount;
					}
					cat.dollarTotal.add(item.amount);
				}
			 }
			}
		}
	
	/**
	 * Takes Category Data and creates a visual summary of that data.
	 * @param categoryList
	 */
	public static void displaySummary(Category[] categoryList){
		//System.out.println("-------------------------------");
		//System.out.println("Spending Summary : ");

		//SelectionSort
		for(int i = 0; i < categoryList.length; i++){
			Category smallest = categoryList[i];

			for (int j = 1; j < categoryList.length; j++){
				if (categoryList[j-1].dollarTotal.compareTo(smallest.dollarTotal) == -1) {
					smallest = categoryList[j];

					Category temp = categoryList[j];
					categoryList[j] = categoryList[j-1];
					categoryList[j-1] = temp;
			}
		}
	}
		// Reverse categoryList
		int left = 0;
		int right = categoryList.length-1;

		while (left < right){
			Category temp = categoryList[left];
			categoryList[left] = categoryList[right];
			categoryList[right] = temp;

			left++;
			right--;
		}

		for (Category cat : categoryList){
			if (cat.size > 0){
				//System.out.println(""+cat.name + " - [" +cat.size +" Items : $" +cat.dollarTotal + " Total Cost]");
			}
	}
}
}
