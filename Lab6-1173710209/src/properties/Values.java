package properties;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 参数
 */
public class Values {

  public static final int n;
  public static final int h;
  public static final int t;
  public static final int N;
  public static final int k;
  public static final int MV;
  public static final int WAIT = 1000;

  //初始化参数
  static {
    Properties properties = new Properties(); 
    InputStream in = Values.class.getResourceAsStream("value.properties");//从配置文件中获取信息
    try {
      properties.load(in); // 加载配置文件中的信息
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    //初始化各项参数
    n = Integer.valueOf(properties.getProperty("n"));
    h = Integer.valueOf(properties.getProperty("h"));
    t = Integer.valueOf(properties.getProperty("t"));
    N = Integer.valueOf(properties.getProperty("N"));
    k = Integer.valueOf(properties.getProperty("k"));
    MV = Integer.valueOf(properties.getProperty("MV"));


  }
}
