
public final class DeluxPizza extends Pizza {
    public DeluxPizza(boolean isVeg) {
        super(isVeg);
        super.addExtraCheese();  // Force-add during construction
        super.addExtraToppings();
    }

    // Prevent modifying extras after initialization
    @Override
    public void addExtraCheese() {
        throw new UnsupportedOperationException("Extras are fixed in Delux Pizza!");
    }

    @Override
    public void addExtraToppings() {
        throw new UnsupportedOperationException("Extras are fixed in Delux Pizza!");
    }
}