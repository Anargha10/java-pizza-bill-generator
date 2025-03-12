
public class Pizza {
    private static final int VEG_BASE_PRICE = 300;
    private static final int NON_VEG_BASE_PRICE = 400;
    private static final int EXTRA_CHEESE_PRICE = 100;
    private static final int EXTRA_TOPPINGS_PRICE = 150;
    private static final int TAKEAWAY_CHARGE = 20;

    private final boolean isVeg;
    private int totalPrice;
    private boolean hasExtraCheese;
    private boolean hasExtraToppings;
    private boolean isTakeaway;

    public Pizza(boolean isVeg) {
        this.isVeg = isVeg;
        this.totalPrice = isVeg ? VEG_BASE_PRICE : NON_VEG_BASE_PRICE;
    }

    /**
     * Adds extra cheese to the pizza.
     * @throws IllegalStateException if cheese already added
     */
    public void addExtraCheese() {
        if (hasExtraCheese) {
            throw new IllegalStateException("Extra cheese already added!");
        }
        this.totalPrice += EXTRA_CHEESE_PRICE;
        this.hasExtraCheese = true;
    }

    /**
     * Adds extra toppings to the pizza.
     * @throws IllegalStateException if toppings already added
     */
    public void addExtraToppings() {
        if (hasExtraToppings) {
            throw new IllegalStateException("Extra toppings already added!");
        }
        this.totalPrice += EXTRA_TOPPINGS_PRICE;
        this.hasExtraToppings = true;
    }

    /**
     * Enables takeaway packaging.
     * @throws IllegalStateException if takeaway already selected
     */
    public void enableTakeaway() {
        if (isTakeaway) {
            throw new IllegalStateException("Takeaway already enabled!");
        }
        this.totalPrice += TAKEAWAY_CHARGE;
        this.isTakeaway = true;
    }

    /**
     * Generates the bill breakdown.
     * @return Formatted bill string
     */
    public String generateBill() {
        StringBuilder bill = new StringBuilder();
        bill.append("Base Pizza: ").append(isVeg ? "Veg" : "Non-Veg")
            .append(" - ₹").append(isVeg ? VEG_BASE_PRICE : NON_VEG_BASE_PRICE).append("\n");
        
        if (hasExtraCheese) {
            bill.append("Extra Cheese: ₹").append(EXTRA_CHEESE_PRICE).append("\n");
        }
        if (hasExtraToppings) {
            bill.append("Extra Toppings: ₹").append(EXTRA_TOPPINGS_PRICE).append("\n");
        }
        if (isTakeaway) {
            bill.append("Takeaway Packaging: ₹").append(TAKEAWAY_CHARGE).append("\n");
        }
        
        bill.append("Total: ₹").append(totalPrice);
        return bill.toString();
    }
}