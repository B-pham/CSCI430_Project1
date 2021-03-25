import java.io.*;
import java.util.*;

public class ProductList implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Product> products = new LinkedList<Product>();
  private static ProductList productList;
  private int productCount = 0;

  private ProductList() {}

  public static ProductList instance() {
    if (productList == null) {
      return (productList = new ProductList());
    } else {
      return productList;
    }
  }

  public boolean insertProduct(Product product) {
    productCount += 1;
    System.out.println(productCount + " product");
    boolean result = products.add(product);
    System.out.println(" products " + result);
    return result;
  }

  /**
   * add multiple products at once
   * @param products
   * @return
   */
  public boolean insertProduct(List<Product> products) {
    productCount += 1;
    System.out.println(productCount + " products");
    return true;
  }

  public Iterator<Product> getProducts() {
    return products.iterator();
  }

  /**
   * get number of product in the list
   */
  public int getProductsCount() {
    return productCount;
  }

  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(productList);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (productList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (productList == null) {
          productList = (ProductList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }

  /**
   * find product by id
   */
  public Product findProduct(String productId) {
    System.out.println("received " + productId);

    for (int i = 0; i < products.size(); i++) {
      System.out.println("searching product...");
      Product product = (Product) products.get(i);

      if (product.getId().equals(productId)) {
        System.out.println("**** found it...");
        return product;
      }
    }
    System.out.println("didn't find anything");
    return null;
  }

  public void getAllProducts() {
    Iterator<Product> ProductsID = this.getProducts();

    System.out.println();
    while (ProductsID.hasNext()) {
      System.out.println(ProductsID.next().getId() + " ");
    }
  }
}
