package stores;

import products.GroceryItem;
import products.Item;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Item> basket;

    public Basket() {
        this.basket = new ArrayList<>();
    }

    public List<Item> getBasket() {
        return basket;
    }

    public void printHatBasket() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("|%2s |%-8s |  %7s  |  %7s  | %6s |%n", "№", "Наименование товара",
                "Цена/за ед.", "Количество", "Общ. стоимость");
        System.out.println("---------------------------------------------------------------------------");
    }

    public boolean isItemsInBasket(String currentTitle, int currentPrice) {
        return basket.contains(new Item(currentTitle, currentPrice));
    }

    public void addBasketItems(String currentTitle, int currentPrice, int numberCount) {
        if (isItemsInBasket(currentTitle, currentPrice)) {
            int index = basket.indexOf(new GroceryItem(currentTitle, currentPrice));
            int count = basket.get(index).getCountProduct();
            int sum = numberCount + count;
            Item item = new Item(currentTitle, currentPrice, sum);
            basket.set(index, item);
        } else {
            basket.add(new Item(currentTitle, currentPrice, numberCount));
        }
    }

    public int deleteItemOutOfBasket(int inputIndex) {
        int sumDel = 0;
        if (inputIndex > 0 && inputIndex <= basket.size()) {
            sumDel = getBasket().get(--inputIndex).getPrice() *
                    basket.get(inputIndex).getCountProduct();

            basket.remove(inputIndex);
        } else {
            System.out.println("Нет товара с таким номером!");
        }
        return sumDel;
    }

    public void clearBasket() {
        basket.clear();
        System.out.println("Корзина очищена!");
    }

    public int totalPrice(int currentPrice, int numberCount) {
        return currentPrice * numberCount;
    }

    public void totalBasket(int totalSum, int countIndex, int emptyBasket) {
        if (basket.size() > emptyBasket) {
            printHatBasket();

            for (int i = 0; i < getBasket().size(); i++) {
                System.out.printf(
                        "|%2s |%-19s |  %11s  |  %10s  | %14s |%n",
                        i + countIndex,
                        getBasket().get(i).getTitle(),
                        getBasket().get(i).getPrice(),
                        getBasket().get(i).getCountProduct(),
                        (getBasket().get(i).getPrice() * getBasket().get(i).getCountProduct())
                );
            }
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf("%73s", "Итого " + totalSum + " руб.");
        } else {
            System.out.println("Нет Продуктов в Корзине!");
        }
    }
}