package se.lernia.lindstrom.max;

public class Main {
    public static void main(String[] args) {
        Product milk = new Product("milk", 20, 10);
        Product eggs = new Product("eggs", 20, 10);
        Product bread = new Product("bread", 30, 3);
        Product butter = new Product("butter", 30, 10);

        CustomDiscount discountChain = getDiscountChain();
        Discount otherDiscountChain = new FridayDiscount(new MilkDiscount(new QuantityDiscount(null)));

        double eggDiscount = discountChain.apply(eggs);
        System.out.println("Rabatt på ägg: " + eggDiscount + "\n" + discountChain.getDescription(eggs) + "\n");

        double butterDiscount = discountChain.apply(butter);
        System.out.println("Rabatt på smör: " + butterDiscount + "\n" + discountChain.getDescription(butter));

        double milkDiscount = otherDiscountChain.apply(milk);
        System.out.println("Rabatt på mjölk: " + milkDiscount + "\n" + otherDiscountChain.getDescription(milk));

        double breadDiscount = otherDiscountChain.apply(bread);
        System.out.println("Rabatt på bröd: " + breadDiscount + "\n" + otherDiscountChain.getDescription(bread) + "\n");

    }

    public static CustomDiscount getDiscountChain() {
        CustomDiscount eggDiscount = new CustomDiscount(
                product -> product.name().equalsIgnoreCase("eggs"),
                product -> product.price() * product.quantity() * 0.07,
                null,
                "7% rabatt på ägg"
        );

        return new CustomDiscount(
                product -> product.quantity() >= 10,
                _ -> 100,
                eggDiscount,
                "100kr rabatt om man köper 10 eller fler produkter"
        );
    }
}