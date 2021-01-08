package applications;

import exceptions.WrongFileTypeException;
import exceptions.WrongTrackNumberException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  private Scanner in;

  public static void main(String[] args) throws WrongTrackNumberException, IOException, WrongFileTypeException {
    Main m = new Main();
    m.start();
  }

  /**
   * ��������.
   * @throws WrongTrackNumberException ����������쳣
   * @throws IOException IO�쳣
   * @throws WrongFileTypeException �ļ����ʹ����쳣
   */
  public void start() throws WrongTrackNumberException, IOException, WrongFileTypeException {
    System.out.println("1.StellarSystem");
    System.out.println("2.AtomStructure");
    System.out.println("3.SocialNetworkCircle");
    System.out.println("����end�˳�");

    int choice;

    while (true) {
      System.out.println("������:");
      in = new Scanner(System.in);
      String s = in.nextLine();
      if (s.matches("[1-3]")) {
        choice = Integer.valueOf(s);
        break;
      } else if (s.equals("end")) {
        System.out.println("ллʹ��");
        System.exit(0);
      } else {
        System.out.println("�Ƿ�����(������1,2,3,end)");
      }
    }

    switch (choice) {
      case 1: {
        StellarSystem.main(null);
        break;
      }
  
      case 2: {
        AtomStructure.main(null);
        break;
      }
  
      case 3: {
        SocialNetworkCircle.main(null);
        break;
      }
      
      default: {
        break;
      }
    }
  }
}
