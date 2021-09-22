package mypackage;

import org.apache.commons.lang3.StringUtils;

import static org.junit.Assert.assertEquals;

public class Test2 {

  public static void main(String[] args)
      throws Exception {
    assertEquals(3, args.length);
    assertEquals("zimbu", args[0]);
    assertEquals("the", args[1]);
    assertEquals("monkey", args[2]);
    assertEquals("hello", System.getenv("ENV1"));
    assertEquals("world", System.getenv("ENV2"));
    assertEquals("boo", System.getProperty("prop1"));
    assertEquals("hoo", System.getProperty("prop2"));

    System.out.println(StringUtils.swapCase("Hello, Bob!"));
  }
}
