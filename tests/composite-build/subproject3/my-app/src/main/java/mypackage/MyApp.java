package mypackage;

public class MyApp {

  public static void main(String[] args)
      throws Exception {
    final MyInterface myInterface = new MyImplementation();
    System.out.println(myInterface.greet("Bob"));
  }
}
