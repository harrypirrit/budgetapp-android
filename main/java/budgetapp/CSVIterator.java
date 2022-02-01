package budgetapp;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

	public static void fetchFile(BufferedReader reader){
		try{
			BufferedReader csvReader = reader;
		} catch (Exception e) {
			e.printStackTrace();
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
		catch (Exception e1) {
			System.out.println("Incorrect File PATH :" +CSV_Path);
			System.out.println("Returning Empty Data");
		}
		return data;
	}


	public static List<String[]> createData(BufferedReader reader){
		List <String[]> data = new ArrayList<String[]>();
		try {
			BufferedReader csvReader = reader;
			try {
				String row = null;
				while((row = csvReader.readLine()) != null) {
					data.add(row.split(","));
				}
			} catch (IOException e){
				System.out.println("IOException Error : " +e);
			}
			catch (NullPointerException e){
			}
		}
		catch (Exception e1) {
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

			// Format Date
			DateFormat originalFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
			DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateFormat = null;
			try {
				dateFormat = originalFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			date = targetFormat.format(dateFormat);

			Item item = new Item(date, description, amount);
			itemList.add(item);
			count++;
		}
		return itemList;
	}
}