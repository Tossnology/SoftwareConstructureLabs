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
 * 猴子生成器
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
   * 开始生成猴子
   */
  public void Begin(){

    int tempMonkeysNumber = 0;
    long time = System.currentTimeMillis();//start time
    while (tempMonkeysNumber < totalMonkeysNumber){

      //第一种情况生成k个猴子
      //第二种情况生成totalMonkeysNumber - tempMonkeysNumber个猴子
      if (tempMonkeysNumber+everyTimeMonkeysNumber<=totalMonkeysNumber){
        //情况1
        for (int i =0;i<everyTimeMonkeysNumber;i++){
          Thread thread = new Thread(RandomMonkeyFactory());
          thread.start();
        }
        tempMonkeysNumber+=everyTimeMonkeysNumber;
      }else {
        //情况2
        for (int i =0;i<totalMonkeysNumber-tempMonkeysNumber;i++){
          Thread thread = new Thread(RandomMonkeyFactory());
          thread.start();
        }
        tempMonkeysNumber = totalMonkeysNumber;
      }

      //暂停t秒
      try {
        Thread.sleep(Values.WAIT*everyTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    //计算吞吐率
    try {
      latch.await();
      long lifeTime = (System.currentTimeMillis()-time)/1000;
      System.out.println("总耗时: "+lifeTime);
      GUI.getTextGUI().addText("总耗时: "+lifeTime);
      System.out.println("吞吐率: "+(Values.N/(lifeTime+0.0)));
      GUI.getTextGUI().addText("吞吐率: "+(Values.N/(lifeTime+0.0)));
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

    v = random.nextInt(mv)+1;//随机速度
    //随机方向
    if ((random.nextInt()&1)==1){
      direction = "L->R";
    }else {
      direction = "R->L";
    }
    //随机选择策略
    switch(random.nextInt(3)){
      case 0:
        //初始默认最佳策略
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
   * 计算公平性
   */
  public static void getFairValue(){
    double sum = 0;
    for (int i =0;i<Values.N-1;i++){
      for (int j = i+1;j<Values.N;j++){
        sum+=(startTime.get(i)-startTime.get(j))*(endTime.get(i)-endTime.get(j))>0?1:-1;
      }
    }
    sum/=nCr(Values.N,2);
    System.out.println("公平性: "+sum);
    GUI.getTextGUI().addText("公平性: "+sum);
  }


  //nCr计算公式
  private static int nCr(int n, int r) {
    if (r==0||n==r) return 1;
    return fact(n).divide(fact(r).multiply(fact(n - r))).intValue();
  }

  //阶乘计算公式
  private static BigInteger fact(int n) {
    BigInteger res = BigInteger.valueOf(1);
    for (int i = 2; i <= n; i++)
      res = res.multiply(BigInteger.valueOf(i));
    return res;
  }
}
