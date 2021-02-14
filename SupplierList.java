import java.io.*;
import java.util.*;

public class SupplierList implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Supplier> suppliers = new LinkedList<Supplier>();
  private static SupplierList supplierList;
  private int supplierCount = 0;

  private SupplierList() {}

  public static SupplierList instance() {
    if (supplierList == null) {
      return (supplierList = new SupplierList());
    } else {
      return supplierList;
    }
  }

  public boolean insertSupplier(Supplier supplier) {
    supplierCount += 1;
    System.out.println(supplierCount + " supplier");
    boolean result = suppliers.add(supplier);
    System.out.println(" supplier " + result);
    return result;
  }

  public Iterator<Supplier> getSuppliers() {
    return suppliers.iterator();
  }

  /**
   * get number of suppliers in the list
   */
  public int getSuppliersCount() {
    return supplierCount;
  }

  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(supplierList);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (supplierList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (supplierList == null) {
          supplierList = (SupplierList) input.readObject();
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
   * find supplier by id
   */
  public Supplier findSupplier(String supplierId) {
    System.out.println("received " + supplierId);

    for (int i = 0; i < suppliers.size(); i++) {
      System.out.println("searching supplier...");
      Supplier supplier = (Supplier) suppliers.get(i);

      if (supplier.getId().equals(supplierId)) {
        System.out.println("**** found it...");
        return supplier;
      }
    }
    System.out.println("didn't find anything");
    return null;
  }
}
