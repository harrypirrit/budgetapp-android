package budgetapp;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CSVIterator {
	/** fields */
	public static String CSV_Path;
	public static boolean SpendingSelected = true;
	public static final String[] KEY_WORDS = {"DEBIT", "7805", "CARD", "EFTPOS"};

	
	/**
	 * Attempts to create a BufferedReader object for the file with a given path.
	 * @param path FilePath input of type String.
	 */
	public static void fetchFile(String path){
		CSV_Path = path;
	try {
		BufferedReader csvReader = new BufferedReader(new FileReader(CSV_Path));
		//createData(csvReader);
		}
	catch (FileNotFoundException e1) {
			System.out.println("Incorrect File PATH :" +CSV_Path);
		}
	}

	/**
	 * Creates initial List of unfiltered data.
	 * @param path
	 * @return a List of String Arrays containing CSV rows and their contents.
	 */
	public static List<String[]> createData(String path){
		CSV_Path = path;
		List <String[]> data = new ArrayList<String[]>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(CSV_Path));
			try {
				while((csvReader.readLine()) != null) {
					String row = csvReader.readLine();
					data.add(row.split(","));
				}
			} catch (IOException e){
				System.out.println("IOException Error : " +e);
			}
			catch (NullPointerException e){
			}
		}
		catch (FileNotFoundException e1) {
			System.out.println("Incorrect File PATH :" +CSV_Path);
			System.out.println("Returning Empty Data");
		}
		return data;
	}

	/**
	 * Creates a filtered version of the input data
	 * @param data unfiltered List of StringArrays to be manipulated.
	 * @return a filtered List of StringArrays.
	 */
	public static List<String[]> filterData(List<String[]> data){
		List<String[]> list = new ArrayList<String[]>();

		for(String[] row : data){
			/** Pick correct values from row and assign them to named variables. */
			String amount = row[6];
			String date = row[0];
			
			/** Clean Keywords from Description */
			String description = ""+row[4]+ row[5];
			for (String i : KEY_WORDS){
				if (description.contains(i)) description = description.replace(i, "");
			}
			description = description.replace('"', ' ').trim();
			System.out.println(System.getProperty("java.version"));

			/** Add attributes into  an Array and feed them into the filtered List to be returned. */
			String[] attributes = {date, description, amount};
			if (SpendingSelected && amount.charAt(0) == '-'){
				list.add(attributes);
			}
		}
		return list;

	}
	
	/**
	 * Creates a List of Items based on a filtered list of rows.
	 * @param list filtered List of StringArrays.
	 * @return a List of Items created from the CSV File.
	 */
	public static ArrayList<Item> createList(List<String[]> list) {
		ArrayList<Item> itemList = new ArrayList<Item>();
		int count = 1;
		for (String[] row : list){
			String date = row[0];
			String description = row[1];
			BigDecimal amount = new BigDecimal(row[2]);
			
			Item item = new Item(date, description, amount);
			itemList.add(item);
			count++;
		}
		return itemList;
	}
}