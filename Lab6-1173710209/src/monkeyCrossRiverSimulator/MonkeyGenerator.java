package monkeyCrossRiverSimulator;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import properties.Values;
import strategy.BestStrategy;
import strategy.Strategy;
import strategy.SameDirectionStrategy;
import strategy.TestStrategy;

/**
 * ����������
 */
public class MonkeyGenerator {

  private final int totalMonkeysNumber = Values.N;
  private final int everyTimeMonkeysNumber = Values.k;
  private final int everyTime = Values.t;
  private final CountDownLatch latch = new CountDownLatch(Values.N);

  //give monkey to access
  public final static Map<Integer,Long> startTime = new ConcurrentHashMap<>();
  public final static Map<Integer,Long> endTime = new ConcurrentHashMap<>();

  private int id = 0;


  //initialize startTime and endTime
  static {
    for (int i =0;i<Values.N;i++){
      startTime.put(i,0L);
      endTime.put(i,0L);
    }
  }

  /**
   * ��ʼ���ɺ���
   */
  public void Begin(){

    int tempMonkeysNumber = 0;
    long time = System.currentTimeMillis();//start time
    while (tempMonkeysNumber < totalMonkeysNumber){

      //��һ���������k������
      //�ڶ����������totalMonkeysNumber - tempMonkeysNumber������
      if (tempMonkeysNumber+everyTimeMonkeysNumber<=totalMonkeysNumber){
        //���1
        for (int i =0;i<everyTimeMonkeysNumber;i++){
          Thread thread = new Thread(RandomMonkeyFactory());
          thread.start();
        }
        tempMonkeysNumber+=everyTimeMonkeysNumber;
      }else {
        //���2
        for (int i =0;i<totalMonkeysNumber-tempMonkeysNumber;i++){
          Thread thread = new Thread(RandomMonkeyFactory());
          thread.start();
        }
        tempMonkeysNumber = totalMonkeysNumber;
      }

      //��ͣt��
      try {
        Thread.sleep(Values.WAIT*everyTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    //����������
    try {
      latch.await();
      long lifeTime = (System.currentTimeMillis()-time)/1000;
      System.out.println("�ܺ�ʱ: "+lifeTime);
      GUI.getTextGUI().addText("�ܺ�ʱ: "+lifeTime);
      System.out.println("������: "+(Values.N/(lifeTime+0.0)));
      GUI.getTextGUI().addText("������: "+(Values.N/(lifeTime+0.0)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Monkey RandomMonkeyFactory(){
    String direction;
    Integer v;
    Integer mv = Values.MV;
    Strategy maker = new BestStrategy();
    Random random = new Random();

    v = random.nextInt(mv)+1;//����ٶ�
    //�������
    if ((random.nextInt()&1)==1){
      direction = "L->R";
    }else {
      direction = "R->L";
    }
    //���ѡ�����
    switch(random.nextInt(3)){
      case 0:
        //��ʼĬ����Ѳ���
        break;
      case 1:
        maker = new TestStrategy();
        break;
      case 2:
        maker = new SameDirectionStrategy();
        break;
      default:
        assert false;
    }

    return new Monkey(id++,direction,v,maker,latch);

  }

  /**
   * ���㹫ƽ��
   */
  public static void getFairValue(){
    double sum = 0;
    for (int i =0;i<Values.N-1;i++){
      for (int j = i+1;j<Values.N;j++){
        sum+=(startTime.get(i)-startTime.get(j))*(endTime.get(i)-endTime.get(j))>0?1:-1;
      }
    }
    sum/=nCr(Values.N,2);
    System.out.println("��ƽ��: "+sum);
    GUI.getTextGUI().addText("��ƽ��: "+sum);
  }


  //nCr���㹫ʽ
  private static int nCr(int n, int r) {
    if (r==0||n==r) return 1;
    return fact(n).divide(fact(r).multiply(fact(n - r))).intValue();
  }

  //�׳˼��㹫ʽ
  private static BigInteger fact(int n) {
    BigInteger res = BigInteger.valueOf(1);
    for (int i = 2; i <= n; i++)
      res = res.multiply(BigInteger.valueOf(i));
    return res;
  }
}
