package CentralObjectTest;

import static org.junit.jupiter.api.Assertions.*;

import centralObject.Stellar;
import org.junit.jupiter.api.Test;

class StellarTest {

  @Test
  void testGetName() {
    Stellar s = new Stellar("Sun", 6.96392e5, 1.9885e30);
    assertEquals(s.getName(), "Sun");
  }

  @Test
  void testGetMass() {
    Stellar s = new Stellar("Sun", 6.96392e5, 1.9885e30);
    assertEquals(s.getMass(), 1.9885e30);
  }

  @Test
  void testGetRadius() {
    Stellar s = new Stellar("Sun", 6.96392e5, 1.9885e30);
    assertEquals(s.getRadius(), 6.96392e5);
  }

}
