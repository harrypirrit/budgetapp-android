package budgetapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class Item implements Parcelable {
	/** fields */
	public String date;
	public String description;
	public BigDecimal amount;
	public Category category;


	/** Constructor */
	public Item (String date, String description, BigDecimal amount ){
		this.date = date;
		this.description = description;
		this.amount = amount;
	}

	// Makes Class Parceable
	protected Item(Parcel in) {
		date = in.readString();
		description = in.readString();
	}

	public static final Creator<Item> CREATOR = new Creator<Item>() {
		@Override
		public Item createFromParcel(Parcel in) {
			return new Item(in);
		}

		@Override
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};

	/** toString method */
	public String toString(){
		String str = "\n {Item Date : " +this.date +"\nItem Description : " +this.description +"\nItem Amount : $" +this.amount + "\nCategory : " +this.category +"\n}-- -- -- --";
		return str;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(date);
		parcel.writeString(description);
	}
}