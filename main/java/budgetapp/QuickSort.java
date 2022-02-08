package budgetapp;

import budgetapp.Category;

public class QuickSort {

    public int partition(Category[] categoryArray, int low, int high){

        Category pivot = categoryArray[high];
        int i = (low-1);
        for (int j=low; j<high; j++){
            if (categoryArray[j].dollarTotal.compareTo(pivot.dollarTotal) == -1 || categoryArray[j].dollarTotal.compareTo(pivot.dollarTotal) == 0){
                i++;

                Category temp = categoryArray[i];
                categoryArray[i] = categoryArray[j];
                categoryArray[j] = temp;
            }
        }
        Category temp = categoryArray[i+1];
        categoryArray[i+1] = categoryArray[high];
        categoryArray[high] = temp;

        return i+1;
    }

    public void sort (Category[] categoryArray, int low, int high) {
        if (low < high) {
            int pi = partition(categoryArray, low, high);
            sort(categoryArray, low, pi - 1);
            sort(categoryArray, pi+1, high);
        }
    }
}
