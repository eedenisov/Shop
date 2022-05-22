import products.Item;
import stores.Basket;
import stores.GroceryShop;
import stores.Shop;

import java.util.*;

/*
Во всем проекте не использовались числа напрямую в коде.
Постарался избежать повторений кода с помощью правила
DRY (Don't Repeat Yourself).
Также применил принципы SOLID, а именно:
1) Принцип единственной ответственности.
 Все классы выполняют только те функции, для которых предназначены;
2) Принцип открытости/закрытости.
Новые классы мы можем научить всему тому, что умеют классы Shop и Item с помощью наследования,
а также добавить новые поля и методы в новых классах;
3) Принцип замены Барбары Лисков.
Классы GroceryShop и GroceryItem имеют возможность сыграть роль предков;
4) Принцип сегрегации интерфейса.
Два интерфейса Payment и Filter выполняют свои роли по отдельности.
 */

public class Main {

    public static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int totalSum = 0;
        int invalid = 0;
        int countIndex = 1;
        int inputSize = 2;
        int onePart = 0;
        int twoPart = 1;
        int emptyBasket = 0;
        String methodPayment = null;

        List<String> titleItems = Arrays.asList(
                "Хлеб", "Булочки", "Пироги",
                "Бананы", "Апельсины", "Помидоры", "Огурцы",
                "Блины", "Пицца", "Салаты"
        );
        List<Integer> priceItems = Arrays.asList(
                48, 15, 150, 80, 69,
                130, 70, 75, 200, 100
        );

        ArrayList<Item> items = new ArrayList<>();

        Basket basket = new Basket();

        Shop shop = new GroceryShop(items);
        shop.buildAllListItems(titleItems, priceItems);
        shop.welcomeShop();

        exit:
        while (true) {
            shop.printAllItems(countIndex);
            shop.infoList();
            String input = scan.nextLine();
            try {
                switch (input) {
                    case "end":
                        System.out.println("Программа завершена!");
                        System.out.println("Ваша корзина: ");
                        basket.totalBasket(totalSum, countIndex, emptyBasket);
                        if (methodPayment != null) {
                            System.out.println(methodPayment);
                        }
                        break exit;
                    case "return":
                        shop.buildAllListItems(titleItems, priceItems);
                        break;
                    case "filter":
                        System.out.println("Выбрать максимальную стоимость товаров");
                        System.out.print("Ввод: ");
                        int inputSorted = Integer.parseInt(scan.nextLine());
                        if (inputSorted > invalid) {
                            shop.filterAndSortedByPrice(inputSorted);
                        }
                        break;
                    case "delete":
                        basket.totalBasket(totalSum, countIndex, emptyBasket);
                        System.out.println();
                        System.out.println("Укажите номер товара для удаления из корзины:");
                        System.out.print("Ввод: ");
                        int inputIndex = Integer.parseInt(scan.nextLine());
                        int sumDel = basket.deleteItemOutOfBasket(inputIndex);
                        totalSum = totalSum - sumDel;
                        break;
                    case "show":
                        basket.totalBasket(totalSum, countIndex, emptyBasket);
                        break;
                    case "clear":
                        basket.clearBasket();

                        break;
                    case "pay":
                        System.out.println("Выберите способ оплаты:");
                        System.out.println("1.Наличные\n2.Безналичные");
                        System.out.print("Ввод:");
                        int inputNumber = Integer.parseInt(scan.nextLine());

                        methodPayment = shop.paymentMethod(inputNumber, invalid);

                        break;
                    default:
                        String[] inputParts = input.split(" ");
                        if (inputParts.length == inputSize) {

                            int numberTitle = Integer.parseInt(inputParts[onePart]) - countIndex;
                            int numberCount = Integer.parseInt(inputParts[twoPart]);

                            if (numberTitle >= invalid && numberTitle < items.size()) {
                                String currentTitle = items.get(numberTitle).getTitle();
                                int currentPrice = items.get(numberTitle).getPrice();

                                basket.addBasketItems(currentTitle, currentPrice, numberCount);
                                totalSum += basket.totalPrice(currentPrice, numberCount);
                            } else {
                                System.out.println("Ошибка ввода!");
                            }
                        } else {
                            System.out.println("Ошибка! Нет товара с таким номером!");
                        }
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Вместо цифр были указаны символы!");
            }
        }
    }
}

