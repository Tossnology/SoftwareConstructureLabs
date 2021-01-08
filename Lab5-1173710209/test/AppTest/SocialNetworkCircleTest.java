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
      assertEquals(e.getMessage(), "src\\txts\\AtomicStructure1.txt (ϵͳ�Ҳ���ָ�����ļ���)");
    } catch (NeedRereadFileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      SocialNetworkCircle.ReadFile(new File("src/txts/SocialNetworkCircle.txt"));

    } catch (NeedRereadFileException e) {
      assertEquals(e.getMessage(), "�ļ������⣬��Ҫ���¶�ȡ�ļ�");

    }

    try {
      SocialNetworkCircle.ReadFile(new File("src/txts/AtomicStructure.txt"));

    } catch (NeedRereadFileException e) {
      assertEquals(e.getMessage(), "�ļ������⣬��Ҫ���¶�ȡ�ļ�");

    }

    SocialNetworkCircle.ReadFile(new File("src/txts/SocialNetworkCircleRight.txt"));

  }

  @Test
  void DiffusivityTest() throws IOException, NeedRereadFileException {
    SocialNetworkCircle.ReadFile(new File("src/txts/SocialNetworkCircleRight.txt"));

    assertEquals(1, SocialNetworkCircle.diffusivity("PonyMa"));
  }
}
