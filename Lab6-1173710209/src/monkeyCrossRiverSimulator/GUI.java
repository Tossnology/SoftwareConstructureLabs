package monkeyCrossRiverSimulator;
import java.awt.HeadlessException;
import java.awt.TextArea;
import javax.swing.JFrame;

/**
 * ��ʾ�����������̵�ϸ��.
 */
public class GUI extends JFrame {
  private static GUI textGUI = new GUI();
  private TextArea textArea = new TextArea();

  private GUI() throws HeadlessException {
    setTitle("���ӹ���ϸ��");
    getContentPane().add(textArea);
    setSize(200,400);
    setVisible(true);
  }

  public static GUI getTextGUI(){
    return textGUI;
  }

  public void addText(String s){
    textArea.append(s+"\n");
  }
}