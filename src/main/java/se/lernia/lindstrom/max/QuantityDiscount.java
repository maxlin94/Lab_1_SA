package se.lernia.lindstrom.max;

public class QuantityDiscount extends BaseDiscount {
    QuantityDiscount(Discount discount) {
        super(discount);
    }

    @Override
    protected boolean isApplicable(Product product) {
        return product.quantity() >= 5;
    }

    @Override
    protected double calculateDiscount(Product product) {
        return product.quantity() * 10;
    }
}
