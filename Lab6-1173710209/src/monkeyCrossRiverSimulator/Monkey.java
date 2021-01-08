package monkeyCrossRiverSimulator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import properties.Values;
import strategy.Strategy;

/**
 * ����.
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
   * ������.
   *
   * @param id ���ӱ�ţ�������ͬ
   * @param direction ���ӷ��� (L->R) �� (R->L)
   * @param v �ٶ�
   * @param maker ���Ӳ���
   * @param latch �̼߳�����
   */
  public Monkey(Integer id, String direction, Integer v, Strategy maker, CountDownLatch latch) {
    this.id = id;
    this.direction = direction;
    this.velocity = v;
    this.maker = maker;
    this.latch = latch;

    System.out.println("monkey "+id+"�����ˣ��ٶ�Ϊ"+velocity);
    GUI.getTextGUI().addText("monkey "+id+"�����ˣ��ٶ�Ϊ"+velocity);
    Ladder.recordLog("monkey "+id+"������");
  }

  /**
   * ��ȡ���.
   *
   * @return id
   */
  public Integer getId() {
    checkRep();
    return id;
  }

  /**
   * ��ȡ����ʱ��.
   *
   * @return
   */
  public long getBirthTime() {
    return birthTime;
  }

  /**
   * ��ȡ����.
   *
   * @return direction
   */
  public String getDirection() {
    checkRep();
    return direction;
  }

  /**
   * ��ȡ�ٶ�.
   *
   * @return velocity
   */
  public Integer getVelocity() {
    return velocity;
  }

  /**
   * ��������ϸ��.
   */
  @Override
  public void run() {
    birthTime = System.currentTimeMillis();//��ȡ����ʱ��
    Ladder ladder = null; //�������ڵ�����.

    //������״̬.
    //1.ѡ������.
    //2.���������ƶ�.
    while (true){
      //������
      synchronized(Monkey.class){
        int choice = maker.decide(this);

        //����ѡ��Ĳ����ж�.
        if (choice == -1) {

        }else {
          //ѡ������.

          ladder = Bridges.getBridges().get(choice);
          //������
          if (ladder.getEntrance(this)){
            break;
            //������һ״̬.
          }
        }
      }

      //ÿ�ξ���Ҫ��1��
      try {
        if (direction.equals("L->R")){
          Ladder.recordLog("monkey " + id + "�����󰶵ȴ����Ѿ�����"
              +(System.currentTimeMillis()-birthTime)/1000
              +"��");
        }else {
          Ladder.recordLog("monkey " + id + "�����Ұ��ȴ����Ѿ�����"
              +(System.currentTimeMillis()-birthTime)/1000
              +"��");
        }
        Thread.sleep(Values.WAIT);

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //״̬2.
    //�ƶ�
    while (true){
      if (ladder.move(this)) {
        //��������.
        break;
      }

      //ÿ�ξ���Ҫ��1��
      try {
        Thread.sleep(Values.WAIT);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    latch.countDown();
    //��ʱ����Ϣ��������������
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
