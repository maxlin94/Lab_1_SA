import org.junit.jupiter.api.Test;
import se.lernia.lindstrom.max.*;


class CustomDiscountTest {
    @Test
    public void MilkDiscountTest() {
        Product milk = new Product("Milk", 29, 10);
        Product bread = new Product("Bread", 30, 10);
        Discount milkDiscount = new MilkDiscount(null);
        assert(milkDiscount.apply(milk) == milk.price() * milk.quantity() * 0.05);
        assert(milkDiscount.apply(bread) == 0);
    }

    @Test
    public void QuantityDiscountTest() {
        Product milk = new Product("Milk", 29, 5);
        Product bread = new Product("Bread", 30, 4);
        Discount quantityDiscount = new QuantityDiscount(null);
        assert(quantityDiscount.apply(milk) == milk.quantity() * 10);
        assert(quantityDiscount.apply(bread) == 0);
    }

    @Test
    public void ChainDiscountTest() {
        Product eggs = new Product("Eggs", 30, 10);
        Product milk = new Product("Milk", 29, 10);

        CustomDiscount eggDiscount = new CustomDiscount(
                product -> product.name().equalsIgnoreCase("eggs"),
                product -> product.price() * product.quantity() * 0.07,
                null,
                "7% rabatt på ägg"
        );

        CustomDiscount discountChain = new CustomDiscount(
                product -> product.quantity() >= 10,
                _ -> 100,
                eggDiscount,
                "100kr rabatt om man köper 10 eller fler produkter"
        );

        assert(discountChain.apply(eggs) == eggs.price() * eggs.quantity() * 0.07 + 100);
        assert(discountChain.apply(milk) == 100);
    }
}
