package Gorcery;
import java.util.*;
class Product{
   private String Grocery;
     private int Price;
    public Product(String Grocery,int Price){
        this.Grocery = Grocery;
        this.Price = Price;
    }

    Product() {
    }

    public String getGrocery(){
        return Grocery;
    }
    public void setGrocery(String Grocery){
        this.Grocery = Grocery;
    }
    public int getPrice(){
        return Price;
    }
    public void setPrice(int Price){
        this.Price = Price;
    }
}

class CustomerCart extends Product{
    private int Quantity;
    private List<CustomerCart> items;
    public CustomerCart() {
        items  =new ArrayList<>();
    }
    public int getQuantity() {
        return Quantity;
    }
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    public void addItem(CustomerCart cart){
        items.add(cart);
    }
    public void displaycart(){
        System.out.println("Here your Shopping List");
        for (CustomerCart item:items) {
//            int t = getQuantity() * getPrice();
            System.out.println(item.getGrocery()+" = "+ item.getQuantity() + "kg");
        }
    }
    public int calculateBill(){
       int total = 0;
       for(CustomerCart item:items){
           total += item.getPrice() * item.getQuantity();
       }
       return total;
    }
    public void viewBill(){
        System.out.print("Your Total Bill is =  ");
        System.out.println(calculateBill() + "₹");
    }



}
public class Star_Bazar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome🙏 to Star Bazar");
        System.out.println("Here our Catalog");
            ArrayList<Product> productList = new  ArrayList<>();
            productList.add(new Product("Rice",250));
            productList.add(new Product("Sugar",42));
            productList.add(new Product("Moong Dal",190));
            productList.add(new Product("Besan",100));
            productList.add(new Product("Atta",69));
            productList.add(new Product("Maida",45));
            productList.add(new Product("Biscuit",40));
            productList.add(new Product("Tea Powder",120));
            productList.add(new Product("SoyaBin",40));
            productList.add(new Product("Oil",110));
            productList.add(new Product("Panner",270));
            productList.add(new Product("Haldi",50));
            productList.add(new Product("Spices",100));
            productList.add(new Product("Fruit",300));
            productList.add(new Product("Chocalte",120));

        System.out.println("--------------");
        for (Product item: productList) {
            System.out.println(item.getGrocery() + ": ₹" + item.getPrice());
        }

        int choice;
        CustomerCart cart = new CustomerCart();

        do {
            System.out.println("1-Enter the Product Name you want to Buy");
            System.out.println("2- View Your Shopping List");
            System.out.println("3- View your total bill");
            System.out.println("4-Exit");
            System.out.println("Enter your Choice");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Product Name");
                    String productName = sc.next();
                    boolean productFound = false;

                    for (Product product : productList) {
                        if (productName.equals(product.getGrocery())) {
                            System.out.println("Enter the Quantity in kg");
                            int quantity = sc.nextInt();
                            CustomerCart cart1 = new CustomerCart();
                            cart1.setGrocery(product.getGrocery());
                            cart1.setPrice(product.getPrice());
                            cart1.setQuantity(quantity);
                            cart.addItem(cart1);

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
                    System.out.println("Here this is your Shopping Cart List");
                    cart.displaycart();
                    break;

                case 3:
                    System.out.println("Here your Total Bill");
                    cart.viewBill();
                    break;
                case 4:
                    System.out.println("Thanks For Shopping in Our Shop");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 4);


    }
}
