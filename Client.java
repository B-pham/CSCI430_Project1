import java.io.*;
import java.util.*;

public class Client implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final String ClientString = "CL";
  private String name;
  private String address;
  private String phone;
  private String id;

  private List<Product> shopCart = new LinkedList<Product>();
  private List<Order> orders = new LinkedList<Order>();

  public Client(String name, String address, String Phone) {
    this.name = name;
    this.address = address;
    this.phone = Phone;
    this.id = ClientString + (MemberIdServer.instance()).getId();
  }

  public List<Product> getShopCart() {
    return this.shopCart;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return (
      "Client{" +
      "name=" +
      name +
      ", address=" +
      address +
      ", phone=" +
      phone +
      ", id=" +
      id +
      ", shopCart=" +
      shopCart +
      "}"
    );
  }
}
