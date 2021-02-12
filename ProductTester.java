import java.util.*;
import java.text.*;
import java.io.*;
public class ProductTester {
  
  public static void main(String[] s) {
     Product b1 = new Product("qq", 15.00, "b1");
     Product b2 = new Product("ee", 10.00, "b2");
     ProductList productList = ProductList.instance();
     productList.insertProduct(b1);
     productList.insertProduct(b2);
     System.out.println(b1.getName() + " should be qq");
     System.out.println(b1.getPrice() + " should be 15.00");
     System.out.println(b1.getId() + " should be b1");
     System.out.println(b2.getName() + " should be ee");
     System.out.println(b2.getPrice() + " should be 10.00");
     System.out.println(b2.getId() + " should be b2");
     Iterator products = productList.getProducts();
     System.out.println("List of products");
     while (products.hasNext()){
       System.out.println(products.next());
     }
  }
}