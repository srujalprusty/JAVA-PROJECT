import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private int price;
    private ImageIcon image;
    private String category;
    private String quantityUnit;

    public Product(String name, int price, String imagePath, String category, String quantityUnit) {
        this.name = name;
        this.price = price;
        this.image = new ImageIcon(imagePath);
        this.category = category;
        this.quantityUnit = quantityUnit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ImageIcon getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }
}

class ShoppingCart {
    private List<Product> items;
    private List<Integer> quantities;

    public ShoppingCart() {
        items = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        items.add(product);
        quantities.add(quantity);
    }

    public void removeItem(int index) {
        items.remove(index);
        quantities.remove(index);
    }

    public List<Product> getItems() {
        return items;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public int calculateBill() {
        int total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice() * quantities.get(i);
        }
        return total;
    }
}

public class GroceryStoreGUI extends JFrame {
    private JTextArea displayArea;
    private ShoppingCart cart;

    public GroceryStoreGUI() {
        cart = new ShoppingCart();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Star Bazar - Grocery Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 4));
        createProductButtons(buttonPanel);

        JPanel actionPanel = new JPanel(new FlowLayout());
        createButton("View Cart", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCart();
            }
        }, actionPanel);

        createButton("View Bill", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBill();
            }
        }, actionPanel);

        createButton("Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        }, actionPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void createProductButtons(JPanel panel) {
        List<Product> productList = getProductList();

        for (Product product : productList) {
            JButton button = new JButton("<html><center>" + product.getName() + "<br>₹" + product.getPrice() +
                    "<br>" + product.getQuantityUnit() + "</center></html>", product.getImage());
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addToCart(product);
                }
            });

            panel.add(button);
        }
    }

    private void createButton(String text, ActionListener listener, JPanel panel) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addToCart(Product product) {
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Quantity:"));
        cart.addItem(product, quantity);
        displayArea.append(quantity + " " + product.getQuantityUnit() + " of " + product.getName() + " added to your cart 🛒\n");
    }

    private void viewCart() {
        displayArea.setText("🛒 Here's your Shopping Cart List:\n");
        List<Product> items = cart.getItems();
        List<Integer> quantities = cart.getQuantities();

        for (int i = 0; i < items.size(); i++) {
            displayArea.append(items.get(i).getName() + " (" + quantities.get(i) +
                    " " + items.get(i).getQuantityUnit() + ") - ₹" + items.get(i).getPrice() +
                    "/- " + items.get(i).getQuantityUnit() + "\n");
        }
    }

    private void viewBill() {
        displayArea.setText("💰 Here's your Total Bill:\n");
        displayArea.append("Total Bill: ₹" + cart.calculateBill() + "\n");
    }

    private void exitApplication() {
        JOptionPane.showMessageDialog(this, "Thank you for shopping at Star Bazar! 🌟");
        System.exit(0);
    }

    private List<Product> getProductList() {
        ArrayList<Product> productList = new ArrayList<>();

        productList.add(new Product("Coca-Cola", 30, "path/to/coca_cola_image.png", "Cold Drink", "500 ml"));
        productList.add(new Product("Potatoes", 40, "path/to/potatoes_image.png", "Vegetable", "kg"));
        productList.add(new Product("Milk", 30, "path/to/milk_image.png", "Daily Use", "liter"));
        productList.add(new Product("Tomatoes", 25, "path/to/tomatoes_image.png", "Vegetable", "kg"));
        productList.add(new Product("Bread", 20, "path/to/bread_image.png", "Daily Use", "unit"));
        productList.add(new Product("Lays Chips", 20, "path/to/lays_chips_image.png", "Snack", "gram"));
        productList.add(new Product("Orange Juice", 50, "path/to/orange_juice_image.png", "Cold Drink", "liter"));
        productList.add(new Product("Onions", 30, "path/to/onions_image.png", "Vegetable", "kg"));
        productList.add(new Product("Toothpaste", 25, "path/to/toothpaste_image.png", "Daily Use", "unit"));
        productList.add(new Product("Bananas", 20, "path/to/bananas_image.png", "Fruit", "kg"));
        productList.add(new Product("Eggs", 60, "path/to/eggs_image.png", "Daily Use", "dozen"));
        productList.add(new Product("Cucumber", 15, "path/to/cucumber_image.png", "Vegetable", "unit"));

        // Add more products here

        return productList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GroceryStoreGUI().setVisible(true);
            }
        });
    }
}
