package srujal;

import java.util.*;

class ProductList {
    private String name;
    private int price;

    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Product Name--  ");
        name = sc.next();
        System.out.print("Enter Product Price--  ");
        price = sc.nextInt();
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class CustomerList extends ProductList {
    private int quantity;
    private List<CustomerList> items;

    public CustomerList() {
        items = new ArrayList<>();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addItem(CustomerList product) {
        items.add(product);
    }

    public int calculateTotal() {
        int total = 0;
        for (CustomerList item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void displayCart2() {
        System.out.println("Customer Cart:");
        for (CustomerList item : items) {
            System.out.println(item.getName() + " " + item.getQuantity() + " kg" );
        }
        System.out.println("Total: $" + calculateTotal());
    }
}

public class Bigbazzar_shopping {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seller Side");
        System.out.println("How many products do you want to add to your shop?");
        int n = sc.nextInt();
        ProductList[] products = new ProductList[n];

        for (int i = 0; i < products.length; i++) {
            products[i] = new ProductList();
            products[i].addProduct();
        }

        int choice;
        CustomerList cart = new CustomerList();

        do {
            System.out.println("1-Enter the Product Name you want to Buy");
            System.out.println("2- View your bill");
            System.out.println("3-Exit");
            System.out.println("Enter your Choice");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Product Name");
                    String productName = sc.next();
                    boolean productFound = false;

                    for (ProductList product : products) {
                        if (productName.equals(product.getName())) {
                            System.out.println("Enter the Quantity in kg");
                            int quantity = sc.nextInt();

                            CustomerList customerProduct = new CustomerList();
                            customerProduct.setName(product.getName());
                            customerProduct.setPrice(product.getPrice());
                            customerProduct.setQuantity(quantity);
                            cart.addItem(customerProduct);

                            System.out.println(quantity + " kg of " + productName + " added to your bill");
                            productFound = true;
                            break;
                        }
                    }

                    if (!productFound) {
                        System.out.println("Product not found");
                    }
                    break;

                case 2:
                    System.out.println("This is your Shopping Bill");
                    cart.displayCart2();
                    break;

                case 3:
                    System.out.println("Exit from the shopping mall");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 3);
    }
}