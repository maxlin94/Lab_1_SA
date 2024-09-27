package se.lernia.lindstrom.max;

public abstract class BaseDiscount implements Discount {
    Discount nextDiscount;

    BaseDiscount(Discount nextDiscount) {
        this.nextDiscount = nextDiscount;
    }

    @Override
    public double apply(Product product) {
        double discount = 0;
        if (isApplicable(product)) {
            discount = calculateDiscount(product);
        }
        if (nextDiscount != null) {
            discount += nextDiscount.apply(product);
        }
        return discount;
    }

    @Override
    public String getDescription(Product product) {
        String description = "";
        if (isApplicable(product)) {
            description = getClass().getSimpleName() + " applied.";
        }

        if (nextDiscount != null) {
            description += "\n" + nextDiscount.getDescription(product) ;
        }

        return description;
    }

    protected abstract boolean isApplicable(Product product);

    protected abstract double calculateDiscount(Product product);
}
