package se.lernia.lindstrom.max;

@FunctionalInterface
public interface CalculateDiscount {
    double apply(Product product);
}