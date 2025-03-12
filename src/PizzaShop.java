import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PizzaShop {
    private static final DateTimeFormatter RECEIPT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static int orderCounter = 1;
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayWelcomeBanner();
            
            while (true) {
                PizzaOrder order = processOrder(scanner);
                if (order == null) break;
                
                generateReceipt(order);
            }
        }
        System.out.println("\nThank you for choosing our Pizza Shop!");
    }

    private static void displayWelcomeBanner() {
        System.out.println("\n*************************************");
        System.out.println("*       WELCOME TO PIZZA PRO!      *");
        System.out.println("*  Where OOP Meets Delicious Code! *");
        System.out.println("*************************************");
    }

    private static PizzaOrder processOrder(Scanner scanner) {
        System.out.println("\nMAIN MENU:");
        System.out.println("1. New Order");
        System.out.println("2. Exit System");
        System.out.print("\nEnter choice (1-2): ");
        
        int choice = getValidatedInput(scanner, 1, 2);
        if (choice == 2) return null;

        Pizza pizza = createPizza(scanner);
        customizeOrder(scanner, pizza);
        return new PizzaOrder(generateOrderId(), pizza);
    }

    private static Pizza createPizza(Scanner scanner) {
        System.out.println("\nPIZZA TYPE:");
        System.out.println("1. Regular Pizza");
        System.out.println("2. Delux Pizza (Auto Cheese & Toppings)");
        System.out.print("\nSelect pizza type (1-2): ");
        
        int typeChoice = getValidatedInput(scanner, 1, 2);
        
        System.out.println("\nPIZZA BASE:");
        System.out.println("1. Vegetarian");
        System.out.println("2. Non-Vegetarian");
        System.out.print("\nSelect base (1-2): ");
        
        int baseChoice = getValidatedInput(scanner, 1, 2);
        boolean isVeg = baseChoice == 1;

        return typeChoice == 1 ? new Pizza(isVeg) : new DeluxPizza(isVeg);
    }

    private static void customizeOrder(Scanner scanner, Pizza pizza) {
        String[] options = pizza instanceof DeluxPizza ? 
            new String[] { 
                "[X] Extra Cheese (Included)", 
                "[X] Extra Toppings (Included)", 
                "Takeaway Packaging", 
                "Finalize Order" 
            } : 
            new String[] { 
                "Add Extra Cheese", 
                "Add Extra Toppings", 
                "Takeaway Packaging", 
                "Finalize Order" 
            };

        while (true) {
            System.out.println("\nCUSTOMIZATION:");
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d. %s%n", i + 1, options[i]);
            }
            System.out.print("\nSelect option (1-4): ");
            
            int choice = getValidatedInput(scanner, 1, 4);
            if (choice == 4) break;

            try {
                switch (choice) {
                    case 1 -> { if (!(pizza instanceof DeluxPizza)) pizza.addExtraCheese(); }
                    case 2 -> { if (!(pizza instanceof DeluxPizza)) pizza.addExtraToppings(); }
                    case 3 -> pizza.enableTakeaway();
                }
            } catch (IllegalStateException e) {
                System.out.println("\nError: " + e.getMessage());
            }
            
            System.out.println("\nCURRENT ORDER STATUS:");
            System.out.println(pizza.generateBill());
        }
    }

    private static void generateReceipt(PizzaOrder order) {
        System.out.println("\n===================================");
        System.out.println("          ORDER RECEIPT           ");
        System.out.println("===================================");
        System.out.printf("Order ID: %s%n", order.id());
        System.out.printf("Time: %s%n%n", LocalDateTime.now().format(RECEIPT_FORMATTER));
        System.out.println(order.pizza().generateBill());
        System.out.println("===================================\n");
    }

    private static String generateOrderId() {
        return "ORD-" + LocalDateTime.now().format(RECEIPT_FORMATTER) + "-" + orderCounter++;
    }

    private static int getValidatedInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) return input;
                System.out.printf("Please enter between %d-%d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    private record PizzaOrder(String id, Pizza pizza) {}
}