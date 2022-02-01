package budgetapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Summary {
	final String[] CATEGORY_NAMES = new String[5];
	
	/**
	 * Takes Category Data and creates a visual summary of that data.
	 * @param categoryHashMap
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
}
