package stores;

import interfaces.Filter;
import interfaces.Payment;
import products.GroceryItem;
import products.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Shop implements Payment, Filter {
    private final List<Item> items;

    public Shop(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void welcomeShop() {
        System.out.println("Рады приветствовать Вас в онлайн магазине!");
    }

    public void infoList() {
        StringBuilder infoList = new StringBuilder();
        infoList.append("Список возможных команд для ввода:\n");
        infoList.append("--------------------------------------------\n");
        infoList.append("`1 1` - выбрать товар и количество\n");
        infoList.append("`return` - вернуть весь список товаров \n");
        infoList.append("`filter` - отобрать список товаров по цене\n");
        infoList.append("`delete` - удалить товар из корзины\n");
        infoList.append("`show` - показать Корзину\n");
        infoList.append("`clear` - очистить Корзину\n");
        infoList.append("`pay` - оплатить весь товар из корзины\n");
        infoList.append("`end` - для выхода из программы\n");
        infoList.append("--------------------------------------------\n");

        System.out.println(infoList);
        System.out.print("Ввод: ");
    }

    public void printHatItems() {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Cписок возможных товаров для покупок:");
        System.out.println("--------------------------------------------");
        System.out.printf("| %2s | %13s | %8s |%n", "№", "Наименование",
                "Цена/за ед.");
        System.out.println("--------------------------------------------");
    }

    public void printAllItems(int countIndex) {
        printHatItems();

        for (int i = 0; i < getItems().size(); i++) {
            System.out.printf(
                    "| %2s | %13s | %11s |%n",
                    i + countIndex,
                    getItems().get(i).getTitle(),
                    getItems().get(i).getPrice());
        }
        System.out.println("--------------------------------------------");
    }

    public void buildAllListItems(List<String> title, List<Integer> price) {
        items.clear();
        for (int i = 0; i < title.size(); i++) {
            items.add(new GroceryItem(title.get(i), price.get(i)));
        }
    }

    @Override
    public String paymentMethod(int inputNumber, int invalid) {
        String methodPayment = null;
        int size = 2;
        if (inputNumber > invalid && inputNumber <= size) {
            switch (inputNumber) {
                case 1:
                    methodPayment = "Оплата будет производиться наличными";
                    break;
                case 2:
                    methodPayment = "Оплата будет производиться безналичными";
                    break;
            }
        } else {
            System.out.println("Ошибка! Неверно указан способ оплаты!");
        }
        return methodPayment;
    }

    @Override
    public void filterAndSortedByPrice(int price) {
        int emptyBasket = 0;
        List<Item> itemsSorted = getItems().stream()
                .sorted(Comparator.comparing(Item::getPrice))
                .filter(s -> s.getPrice() <= price)
                .collect(Collectors.toList());
        items.clear();
        items.addAll(itemsSorted);
        if (items.size() == emptyBasket) {
            System.out.println("На данную стоимость товаров нет!");
        }
    }

    @Override
    public String toString() {
        return "Shop{" +
                "items=" + items +
                '}';
    }
}
