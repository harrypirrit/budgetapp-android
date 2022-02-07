package budgetapp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Summary {
	/**
	 * Takes Category Data and creates a visual summary of that data.
	 * @param categoryHashMap hashmap of categories
	 */
	public static void displaySummary(HashMap<String, Category> categoryHashMap){
		System.out.println("-------------------------------");
		System.out.println("Spending Summary : ");

		//SelectionSort
//		for(int i = 0; i < categoryList.length; i++){
//			Category smallest = categoryList[i];
//
//			for (int j = 1; j < categoryList.length; j++){
//				if (categoryList[j-1].dollarTotal.compareTo(smallest.dollarTotal) == -1) {
//					smallest = categoryList[j];
//
//					Category temp = categoryList[j];
//					categoryList[j] = categoryList[j-1];
//					categoryList[j-1] = temp;
//			}
//		}
//	}

		for (Map.Entry<String, Category> set : categoryHashMap.entrySet()) {
			System.out.println(set.getKey() + " - [" +set.getValue().size +" Items : $" +set.getValue().dollarTotal + " Total Cost]");
		}

}

	public static Category[] getCategoryArray(HashMap<String, Category> categoryHashMap){
		int size = categoryHashMap.size();
		int count = 0;
		Category[] categoryArray = new Category[size];

		for (Map.Entry<String, Category> set : categoryHashMap.entrySet()) {
			categoryArray[count] = set.getValue();
			count++;
		}

		return categoryArray;

	}

	public static BigDecimal summaryGetTotalAmount(Category[] categoryArray){
		BigDecimal total = new BigDecimal(0);
		for (Category category : categoryArray){
			total = total.add(category.dollarTotal);
		}
		return total;
	}
}
