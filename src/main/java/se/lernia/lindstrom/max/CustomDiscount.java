package se.lernia.lindstrom.max;

public class CustomDiscount {
    private final IsApplicable isApplicable;
    private final CalculateDiscount calculateDiscount;
    private final CustomDiscount nextDiscount;
    private final String description;


    public CustomDiscount(IsApplicable isApplicable, CalculateDiscount calculateDiscount, CustomDiscount nextDiscount, String description) {
        this.isApplicable = isApplicable;
        this.calculateDiscount = calculateDiscount;
        this.nextDiscount = nextDiscount;
        this.description = description;
    }

    public double apply(Product product) {
        double discount = 0;
        if (isApplicable.apply(product)) {
            discount = calculateDiscount.apply(product);
        }
        if (nextDiscount != null) {
            discount += nextDiscount.apply(product);
        }
        return discount;
    }

    public String getDescription(Product product) {
        StringBuilder descriptionBuilder = new StringBuilder();

        if (isApplicable.apply(product)) {
            double discountAmount = calculateDiscount.apply(product);
            descriptionBuilder.append(String.format("%s: %.2f rabatt.", description, discountAmount));
        }
        if (nextDiscount != null) {
            if (!descriptionBuilder.isEmpty()) {
                descriptionBuilder.append("\n");
            }
            descriptionBuilder.append(nextDiscount.getDescription(product));
        }
        return descriptionBuilder.toString();
    }
}
