package budgetapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Category implements Parcelable {
	/** fields */
	public String name; 
	public BigDecimal dollarTotal;
	public int size;

	/** Constructor */
	public Category(String n){
		this.name = n;
		this.dollarTotal = BigDecimal.valueOf(0);
		this.size = 0;
	}

	/** toString method */
	public String toString() {
		return ""+this.name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeSerializable(this.dollarTotal);
		dest.writeInt(this.size);
	}

	public void readFromParcel(Parcel source) {
		this.name = source.readString();
		this.dollarTotal = (BigDecimal) source.readSerializable();
		this.size = source.readInt();
	}

	protected Category(Parcel in) {
		this.name = in.readString();
		this.dollarTotal = (BigDecimal) in.readSerializable();
		this.size = in.readInt();
	}

	public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
		@Override
		public Category createFromParcel(Parcel source) {
			return new Category(source);
		}

		@Override
		public Category[] newArray(int size) {
			return new Category[size];
		}
	};
}
