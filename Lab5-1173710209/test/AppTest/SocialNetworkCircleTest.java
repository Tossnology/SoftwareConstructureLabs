package AppTest;

import static org.junit.jupiter.api.Assertions.*;

import applications.AtomStructure;
import applications.SocialNetworkCircle;
import exceptions.NeedRereadFileException;
import exceptions.WrongFileTypeException;
import exceptions.WrongTrackNumberException;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class SocialNetworkCircleTest {

  @Test
  void ReadFileTest() throws IOException, NeedRereadFileException {
    try {
      SocialNetworkCircle.ReadFile(new File("src/txts/AtomicStructure1.txt"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      assertEquals(e.getMessage(), "src\\txts\\AtomicStructure1.txt (系统找不到指定的文件。)");
    } catch (NeedRereadFileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      SocialNetworkCircle.ReadFile(new File("src/txts/SocialNetworkCircle.txt"));

    } catch (NeedRereadFileException e) {
      assertEquals(e.getMessage(), "文件有问题，需要重新读取文件");

    }

    try {
      SocialNetworkCircle.ReadFile(new File("src/txts/AtomicStructure.txt"));

    } catch (NeedRereadFileException e) {
      assertEquals(e.getMessage(), "文件有问题，需要重新读取文件");

    }

    SocialNetworkCircle.ReadFile(new File("src/txts/SocialNetworkCircleRight.txt"));

  }

  @Test
  void DiffusivityTest() throws IOException, NeedRereadFileException {
    SocialNetworkCircle.ReadFile(new File("src/txts/SocialNetworkCircleRight.txt"));

    assertEquals(1, SocialNetworkCircle.diffusivity("PonyMa"));
  }
}
