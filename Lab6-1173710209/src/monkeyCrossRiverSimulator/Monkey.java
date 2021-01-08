package monkeyCrossRiverSimulator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import properties.Values;
import strategy.Strategy;

/**
 * 猴子.
 */
public class Monkey implements Runnable {

  //fields
  private final Integer id;
  private final String direction;
  private final Integer velocity;
  private final Strategy maker;
  private final CountDownLatch latch;
  private long birthTime ;

  //Abstract Function
  // AF(id,direction,velocity,maker,birthTime) = a monkey with id direction velocity and is a thread at
  // the same birthTime.
  //Rep Invariant
  //  direction should be "L->R" or "R->L"
  //  velocity > 0 && <= Values.MV
  //  id is unique
  //  birthTime >=0
  //Safe From Rep Exposure
  // all fields are immutable and be followed by private final.

  /**
   * 构造器.
   *
   * @param id 猴子编号，各不相同
   * @param direction 过河方向 (L->R) 或 (R->L)
   * @param v 速度
   * @param maker 过河策略
   * @param latch 线程计数器
   */
  public Monkey(Integer id, String direction, Integer v, Strategy maker, CountDownLatch latch) {
    this.id = id;
    this.direction = direction;
    this.velocity = v;
    this.maker = maker;
    this.latch = latch;

    System.out.println("monkey "+id+"出生了，速度为"+velocity);
    GUI.getTextGUI().addText("monkey "+id+"出生了，速度为"+velocity);
    Ladder.recordLog("monkey "+id+"出生了");
  }

  /**
   * 获取编号.
   *
   * @return id
   */
  public Integer getId() {
    checkRep();
    return id;
  }

  /**
   * 获取出生时间.
   *
   * @return
   */
  public long getBirthTime() {
    return birthTime;
  }

  /**
   * 获取方向.
   *
   * @return direction
   */
  public String getDirection() {
    checkRep();
    return direction;
  }

  /**
   * 获取速度.
   *
   * @return velocity
   */
  public Integer getVelocity() {
    return velocity;
  }

  /**
   * 过河流程细节.
   */
  @Override
  public void run() {
    birthTime = System.currentTimeMillis();//获取出生时间
    Ladder ladder = null; //猴子所在的梯子.

    //有两种状态.
    //1.选择梯子.
    //2.在梯子上移动.
    while (true){
      //上梯子
      synchronized(Monkey.class){
        int choice = maker.decide(this);

        //根据选择的策略行动.
        if (choice == -1) {

        }else {
          //选择梯子.

          ladder = Bridges.getBridges().get(choice);
          //上梯子
          if (ladder.getEntrance(this)){
            break;
            //进入下一状态.
          }
        }
      }

      //每次决策要等1秒
      try {
        if (direction.equals("L->R")){
          Ladder.recordLog("monkey " + id + "正在左岸等待，已经出生"
              +(System.currentTimeMillis()-birthTime)/1000
              +"秒");
        }else {
          Ladder.recordLog("monkey " + id + "正在右岸等待，已经出生"
              +(System.currentTimeMillis()-birthTime)/1000
              +"秒");
        }
        Thread.sleep(Values.WAIT);

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //状态2.
    //移动
    while (true){
      if (ladder.move(this)) {
        //下了梯子.
        break;
      }

      //每次决策要等1秒
      try {
        Thread.sleep(Values.WAIT);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    latch.countDown();
    //把时间信息传给猴子生成器
    MonkeyGenerator.startTime.put(id,birthTime/1000);
    MonkeyGenerator.endTime.put(id,System.currentTimeMillis()/1000);

  }

  //checkRep
  private void checkRep(){
    //  direction should be "L->R" or "R->L"
    //  velocity > 0 && <= Values.MV
    //  id is unique
    //  birthTime >=0
    assert direction.equals("L->R")||direction.equals("R->L");
    assert velocity>0 && velocity<=Values.MV;
    assert birthTime >=0;

  }
}
