package Grocery;

import java.util.*;

class Product {
    private String Grocery;
    private int Price;
    private String image;
    private String category;
    private String quantityUnit;

    public Product(String Grocery, int Price, String image, String category, String quantityUnit) {
        this.Grocery = Grocery;
        this.Price = Price;
        this.image = image;
        this.category = category;
        this.quantityUnit = quantityUnit;
    }

    Product() {
    }

    public String getGrocery() {
        return Grocery;
    }

    public void setGrocery(String Grocery) {
        this.Grocery = Grocery;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }
}

class CustomerCart extends Product {
    private int Quantity;
    public List<CustomerCart> items;

    public CustomerCart() {
        items = new ArrayList<>();
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void addItem(CustomerCart cart) {
        items.add(cart);
    }
    public void removeItem(CustomerCart cart){
        items.remove(cart);
    }

    public void displayCart() {
        System.out.println("🛒 Your Shopping Cart:");
        for (CustomerCart item : items) {
            int t = item.getPrice() * item.getQuantity();
            System.out.println(item.getGrocery() + " (" + item.getQuantity() + " " + item.getQuantityUnit() + ") - ₹" + item.getPrice() +" = Total-Price-->" +t+"₹");
        }
    }

    public int calculateBill() {
        int total = 0;
        for (CustomerCart item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void viewBill() {
        System.out.println("💰 Your Total Bill: ₹" + calculateBill());
    }
}

public class Star_Bazar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("🌟 Welcome to Star Bazar - Your One-Stop Grocery Store 🌟");
        System.out.println("About Our Store:");
        System.out.println("Star Bazar offers a wide range of high-quality groceries. Shop with us for a delightful and convenient experience.");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("Coca-Cola", 30, "🥤", "Cold Drink", "500 ml"));
        productList.add(new Product("Potatoes", 40, "🥔", "Vegetable", "kg"));
        productList.add(new Product("Milk", 30, "🥛", "Daily Use", "liter"));
        productList.add(new Product("Tomatoes", 25, "🍅", "Vegetable", "kg"));
        productList.add(new Product("Bread", 20, "🍞", "Daily Use", "unit"));
        productList.add(new Product("Lays Chips", 20, "🍟", "Snack", "gram"));
        productList.add(new Product("Orange Juice", 50, "🍊", "Cold Drink", "liter"));
        productList.add(new Product("Onions", 30, "🧅", "Vegetable", "kg"));
        productList.add(new Product("Toothpaste", 25, "🦷", "Daily Use", "unit"));
        productList.add(new Product("Bananas", 20, "🍌", "Fruit", "kg"));
        productList.add(new Product("Eggs", 60, "🥚", "Daily Use", "dozen"));
        productList.add(new Product("Cucumber", 15, "🥒", "Vegetable", "unit"));

        System.out.println("\n🛍️ Explore Our Catalog by Categories:");
        System.out.println("--------------");
        System.out.println("1. Cold Drinks");
        System.out.println("2. Vegetables");
        System.out.println("3. Daily Use");
        System.out.println("4. Snacks");
        System.out.println("5. Fruits");
        System.out.println("--------------");

        for (Product item : productList) {
            System.out.println(item.getGrocery()+" "+item.getImage() + " (" + item.getQuantityUnit() + ") - ₹" + item.getPrice() + "/-" +" "+ item.getQuantityUnit());
        }

        int choice;
        CustomerCart cart = new CustomerCart();

        do {
            System.out.println("\nSelect an option:");
            System.out.println("1- Add a Product to Cart");
            System.out.println("2- Remove a Product from cart");
            System.out.println("3- View Your Shopping Cart");
            System.out.println("4- View Your Total Bill");
            System.out.println("5- Exit");
            System.out.print("Enter your Choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Product Name to cart: ");
                    String productName = sc.next();
                    boolean productFound = false;

                    for (Product product : productList) {
                        if (productName.equalsIgnoreCase(product.getGrocery())) {
                            System.out.print("Enter the Quantity: ");
                            int quantity = sc.nextInt();
                            CustomerCart cart1 = new CustomerCart();
                            cart1.setGrocery(product.getGrocery());
                            cart1.setPrice(product.getPrice());
                            cart1.setQuantity(quantity);
                            cart1.setCategory(product.getCategory());
                            cart1.setQuantityUnit(product.getQuantityUnit());
                            cart.addItem(cart1);

                            System.out.println("\n" + quantity + " " + product.getQuantityUnit() + " of " + productName + " added to your cart 🛒");
                            productFound = true;
                            break;
                        }
                    }

                    if (!productFound) {
                        System.out.println("Product not found 🚫");
                    }
                    break;
                case 2:
                    System.out.print("\n Enter the Product you want to remove: ");
                    String product_remove = sc.next();
                    for (CustomerCart item : cart.items) {
                        if (product_remove.equalsIgnoreCase(item.getGrocery())) {
                            cart.removeItem(item);
                            System.out.println("\n" + product_remove + " is removed from your cart");
                            break;
                        }
                    }
                case 3:
                    System.out.println("\n🛒 Here's your Shopping Cart List:");
                    cart.displayCart();
                    break;

                case 4:
                    System.out.println("\n💰 Here's your Total Bill:");
                    cart.viewBill();
                    break;

                case 5:
                    System.out.println("\nThank you for shopping at Star Bazar! 🌟");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }
}
