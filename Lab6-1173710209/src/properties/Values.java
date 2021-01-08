package properties;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ����
 */
public class Values {

  public static final int n;
  public static final int h;
  public static final int t;
  public static final int N;
  public static final int k;
  public static final int MV;
  public static final int WAIT = 1000;

  //��ʼ������
  static {
    Properties properties = new Properties(); 
    InputStream in = Values.class.getResourceAsStream("value.properties");//�������ļ��л�ȡ��Ϣ
    try {
      properties.load(in); // ���������ļ��е���Ϣ
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    //��ʼ���������
    n = Integer.valueOf(properties.getProperty("n"));
    h = Integer.valueOf(properties.getProperty("h"));
    t = Integer.valueOf(properties.getProperty("t"));
    N = Integer.valueOf(properties.getProperty("N"));
    k = Integer.valueOf(properties.getProperty("k"));
    MV = Integer.valueOf(properties.getProperty("MV"));


  }
}
