package CentralObjectTest;

import static org.junit.jupiter.api.Assertions.*;

import centralObject.Nucleus;
import org.junit.jupiter.api.Test;

class NucleusTest {

  @Test
  void testGetName() {
    Nucleus n = new Nucleus("Er");
    assertEquals(n.getName(), "Er");
  }

}
