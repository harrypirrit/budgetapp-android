package budgetapp;
import static budgetapp.CSVIterator.*;
import static budgetapp.UserIterator.*;
import static budgetapp.Summary.*;

import java.util.List;
import java.util.ArrayList;


public class AppCall {
	public static String CSV_Path;

	
	public static void main(String[] args) {
		CSV_Path = "/users/harrypirrit/Desktop/Code/19JulytoAugust.csv";

		// CSVIterator
		List<String[]> data = createData(CSV_Path);
		List<String[]> filtered_data = filterData(data);

		ArrayList<Item> itemList = createList(filtered_data);
		Item[] itemArray;

		//UserIterator
		Category[] categoryList = initCategories();
		itemArray = categorizeItems(itemList, categoryList);

		//Summary
		updateCategoryData(itemArray, categoryList);
		displaySummary(categoryList);


		//System.out.println(itemList.toString());	
	}
	
	
}
