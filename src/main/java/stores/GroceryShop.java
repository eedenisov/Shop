package stores;

import products.Item;

import java.util.List;

public class GroceryShop extends Shop {
    public GroceryShop(List<Item> items) {
        super(items);
    }

    @Override
    public void welcomeShop() {
        super.welcomeShop();
    }

    @Override
    public void infoList() {
        super.infoList();
    }

    @Override
    public void printAllItems(int countIndex) {
        super.printAllItems(countIndex);
    }

    @Override
    public void buildAllListItems(List<String> title, List<Integer> price) {
        super.buildAllListItems(title, price);
    }

    @Override
    public void filterAndSortedByPrice(int price) {
        super.filterAndSortedByPrice(price);
    }

    @Override
    public String paymentMethod(int inputNumber, int invalid) {
        return super.paymentMethod(inputNumber, invalid);
    }
}