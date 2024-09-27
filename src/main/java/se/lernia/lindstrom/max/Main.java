package se.lernia.lindstrom.max;

public class Main {
    public static void main(String[] args) {
        Product milk = new Product("milk", 20, 10);
        Product bread = new Product("bread", 30, 3);

        Discount discountChain = new FridayDiscount(new MilkDiscount(new QuantityDiscount(null)));

        double milkDiscount = discountChain.apply(milk);
        System.out.println("Rabatt på mjölk: " + milkDiscount + "\n" + discountChain.getDescription(milk) + "\n");

        double breadDiscount = discountChain.apply(bread);
        System.out.println("Rabatt på bröd: " + breadDiscount + "\n" + discountChain.getDescription(bread));
    }
}