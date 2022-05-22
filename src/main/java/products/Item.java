package products;

import java.util.Objects;

public class Item {
    private final String title;
    private final int price;
    private int countProduct = 0;

    public Item(String product, int price, int countProduct) {
        this.title = product;
        this.price = price;
        this.countProduct = countProduct;
    }

    public Item(String product, int price) {
        this.title = product;
        this.price = price;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getPrice() == item.getPrice() && Objects.equals(getTitle(), item.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }

    @Override
    public String toString() {
        return title + " - " + price + " руб.";
    }
}
