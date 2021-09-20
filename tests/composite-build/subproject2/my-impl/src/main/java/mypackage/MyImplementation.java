package mypackage;

public class MyImplementation
    implements MyInterface {

  @Override
  public String greet(String name) {
    return String.format("Hello, %s!", name);
  }
}
