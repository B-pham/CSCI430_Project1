import java.util.*;
import java.text.*;
import java.io.*;
public class supplierTest {
	public static void main(String[] s) {
		Supplier test1 = new Supplier(123);
		test1.createOrder();
		test1.setID(12);
		System.out.println("this is the order list: ");
		test1.returnList();
		
		
	}
}
