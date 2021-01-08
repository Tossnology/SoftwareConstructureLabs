package monkeyCrossRiverSimulator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import properties.Values;

/** 
 * ����. 
 * mutable class. 
 */
public class Ladder {

  // fields
  private final Map<Integer, Optional<Monkey>> map = new ConcurrentHashMap<>();
  private final Integer id;
  private final Lock lock = new ReentrantLock();
  private String direction ;
  private Integer velocity;

  // Abstract Function
  //  AF(id,map,direction,velocity) = a ladder with id can contain monkey.
  // Rep Invariant
  // the length of map is h.
  // Safe from rep exposure
  //  all the reps are private and most are final.
  //  direction cannot be changed by others.

  // constructor
  public Ladder(Integer id) {
    this.id = id;
    //��ʼ��
    for (int i = 0; i < Values.h; i++) {
      map.put(i, Optional.empty());
    }
    velocity = Values.h;
    checkRep();
  }

  //��ȡ���
  public Integer getId() {
    return id;
  }

  /**
   * �ж��Ƿ���������
   *
   * @param monkey caller
   * @return the result
   */
  public synchronized boolean getEntrance(Monkey monkey){
    // check the monkey's direction
    if (monkey.getDirection().equals("L->R")){
      return getLeftEntrance(monkey);
    }else {
      return getRightEntrance(monkey);
    }

  }

  /**
   * �ж��Ƿ������������
   *
   * @param monkey �����������ҷ���Ϊ�������ҵĺ���
   * @return whether succeed
   */
  public synchronized boolean getLeftEntrance(Monkey monkey) {
    //check
    checkRep();
    assert !isOnLadder(monkey);
    assert monkey.getDirection().equals("L->R");

    //left entrance should be empty
    if (!map.get(0).isPresent()){
      map.put(0,Optional.of(monkey));
      System.out.println("monkey "+monkey.getId()+" �ڰ�������"+getId()+"�����ӵ����");
      GUI.getTextGUI().addText("monkey "+monkey.getId()+" �ڰ�������"+getId()+"�����ӵ����");
      direction = "L->R";
      if (monkey.getVelocity()<velocity) velocity = monkey.getVelocity();
      return true;
    }
    return false;
  }

  /**
   * �ж��Ƿ���Ұ���������
   *
   * @param monkey ���������ϵĺ���
   * @return whether succeed
   */
  public synchronized boolean getRightEntrance(Monkey monkey) {
    //check
    checkRep();
    assert !isOnLadder(monkey);
    assert monkey.getDirection().equals("R->L");

    //right entrance should be empty
    if (!map.get(map.size()-1).isPresent()){
      map.put(map.size()-1,Optional.of(monkey));
      System.out.println("monkey "+monkey.getId()+" �ڰ�������"+getId()+"�����ӵ��Ҷ�");
      GUI.getTextGUI().addText("monkey "+monkey.getId()+" �ڰ�������"+getId()+"�����ӵ��Ҷ�");
      direction = "R->L";
      if (monkey.getVelocity()<velocity) velocity = monkey.getVelocity();
      return true;
    }
    return false;
  }

  /**
   * �ƶ�.
   *
   * @param monkey �����ϵĺ���.
   * @return ���������ӷ���true,��֮false.
   */
  public synchronized boolean move(Monkey monkey){
    //check
    checkRep();
    assert isOnLadder(monkey);

    int index = -1;
    int nextMonkey = -1;
    int velocity;


    // get index;
    for (Map.Entry<Integer, Optional<Monkey>> entry : map.entrySet()) {
      if (entry.getValue().isPresent()&&monkey.equals(entry.getValue().get())) {
        index = entry.getKey();
      }
    }
    if (monkey.getDirection().equals("L->R")) {
      //monkey move from left to right
      //get nextMonkey
      for (int i = index+1;i<map.size();i++){
        if ( map.get(i).isPresent()) {
          nextMonkey = i;
          break;
        }
      }

      // get velocity
      if (nextMonkey != -1) {
        // there is monkey before you
        velocity = Math.min(nextMonkey - index - 1, monkey.getVelocity());

        // move monkey
        lock.lock();
        map.put(index, Optional.empty());
        recordLog("monkey "+monkey.getId()+"��"+id+"����"+(index + monkey.getVelocity()+1)+"̤�崦����,�Ѿ�����"
            +(System.currentTimeMillis()-monkey.getBirthTime())/1000
            +"��");
        System.out.println("monkey "+monkey.getId()+"��"+id+"��������");
        GUI.getTextGUI().addText("monkey "+monkey.getId()+"��"+id+"��������");
        if (map.get(index + velocity).isPresent()) {
          System.out.println("monkey "+monkey.getId()+"��"+id+"��������");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+"��"+id+"��������");
          lock.unlock();
          return false;
        }
        map.put(index + velocity, Optional.of(monkey));
        lock.unlock();
      } else {
        // no monkey before you
        if (monkey.getVelocity() + index >= map.size()) {
          // monkey can go down the bridge
          // move monkey
          lock.lock();
          map.put(index, Optional.empty());
          System.out.println("monkey "+monkey.getId()+" ����"+id+"������,�ɹ�");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+" ����"+id+"������,�ɹ�");
          recordLog("monkey "+monkey.getId()+"��"+id+"����,�ɹ�������!"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"��");
          lock.unlock();
          return true;
        } else {
          // monkey move itself velocity
          // move monkey
          lock.lock();
          map.put(index, Optional.empty());
          System.out.println("monkey "+monkey.getId()+"��"+id+"��������");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+"��"+id+"��������");
          if (map.get(index + monkey.getVelocity()).isPresent()) {
            System.out.println("monkey "+monkey.getId()+"��"+id+"��������");
            GUI.getTextGUI().addText("monkey "+monkey.getId()+"��"+id+"��������");
            lock.unlock();
            return false;
          }
          map.put(index + monkey.getVelocity(), Optional.of(monkey));
          recordLog("monkey "+monkey.getId()+"��"+id+"����"+(index + monkey.getVelocity()+1)+"��������,�Ѿ�����"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"��");
          lock.unlock();
        }
      }

      checkRep();
      return false;
    }else if (monkey.getDirection().equals("R->L")){
      //monkey move from right to left
      // get nextMonkey;
      for (int i = index-1;i<map.size()&&i>=0;i--){
        if ( map.get(i).isPresent()) {
          nextMonkey = i;
          break;
        }
      }

      // get velocity
      if (nextMonkey != -1) {
        // there is monkey before you
        velocity = Math.min(-nextMonkey + index - 1, monkey.getVelocity());

        // move monkey
        lock.lock();
        map.put(index, Optional.empty());
        if (map.get(index - velocity).isPresent()) {
          System.out.println("monkey "+monkey.getId() +"��"+id+"��������");
          GUI.getTextGUI().addText("monkey "+monkey.getId() +"��"+id+"��������");
          lock.unlock();
          return false;
        }
        System.out.println("monkey "+monkey.getId()+"��"+id+"��������");
        GUI.getTextGUI().addText("monkey "+monkey.getId()+"��"+id+"��������");
        map.put(index - velocity, Optional.of(monkey));
        recordLog("monkey "+monkey.getId()+"��"+id+"����"+(index + monkey.getVelocity()+1)+"̤�崦����,�Ѿ�����"
            +(System.currentTimeMillis()-monkey.getBirthTime())/1000
            +"��");
        lock.unlock();
      } else {
        // no monkey before you
        if (-monkey.getVelocity() + index <0) {
          // monkey can go down the bridge
          // move monkey
          lock.lock();
          map.put(index, Optional.empty());
          System.out.println("monkey "+monkey.getId()+" ����"+getId()+"������,�ɹ�");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+" ����"+getId()+"������,�ɹ�");
          recordLog("monkey "+monkey.getId()+"��"+id+"������,�ɹ�������!��ʱ"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"��");
          lock.unlock();
          return true;
        } else {
          // monkey move itself velocity
          // move monkey
          lock.lock();
          map.put(index, Optional.empty());
          if (map.get(index - monkey.getVelocity()).isPresent()) {
            System.out.println("monkey "+monkey.getId()+"��"+id+"��������");
            GUI.getTextGUI().addText("monkey "+monkey.getId()+"��"+id+"��������");
            lock.unlock();
            return false;
          }
          System.out.println("monkey "+monkey.getId()+"��"+id+"��������");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+"��"+id+"��������");
          map.put(index - monkey.getVelocity(), Optional.of(monkey));
          recordLog("monkey "+monkey.getId()+"��"+id+"����"+(index + monkey.getVelocity()+1)+"̤�崦����,�Ѿ�����"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"��");
          lock.unlock();
        }
      }

      checkRep();
      return false;
    }else {
      assert false;
      //this should not be reached
    }
    return false;
  }

  /**
   * �������Ƿ���������
   *
   * @param monkey �����ĺ���
   * @return �ھͷ���true
   */
  private boolean isOnLadder(Monkey monkey){
    checkRep();
    return map.containsValue(Optional.of(monkey));
  }

  /**
   * ��������Ƿ��ǿյ�
   *
   * @return �Ƿ���true
   */
  public synchronized boolean isEmpty(){
      for (Map.Entry entry:map.entrySet()){
        if (!entry.getValue().equals(Optional.empty())) return false;
      }
      return true;
  }

  /**
   * ������Ƿ�û�к���
   *
   * @return �Ƿ���true
   */
  public synchronized boolean isLeftEmpty(){
      if (map.get(0).isPresent()) return false;
      return true;
  }

  /**
   * ����Ұ��Ƿ�û�к���
   *
   * @return �Ƿ���true
   */
  public synchronized boolean isRightEmpty(){
    if (map.get(map.size()-1).isPresent()) return false;
    return true;
  }

  /**
   * ��ȡ����.
   *
   * @return "L->R" or "R->L"
   */
  public String getDirection(){
    return direction;
  }

  /**
   * ��ȡ���������ٶ�.
   *
   * @return the slowest monkey's velocity.
   */
  public Integer getVelocity(){
    return velocity;
  }

  /**
   * ��¼��־.
   *
   * @param message Ҫ��¼����Ϣ
   */
  public static void recordLog(String message){
    Logger logger  = LoggerFactory.getLogger(Ladder.class);
    logger.info(message);
  }

  //checkRep
  private void checkRep() {
    assert map.size() == Values.h;
  }
}
