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
 * 梯子. 
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
    //初始化
    for (int i = 0; i < Values.h; i++) {
      map.put(i, Optional.empty());
    }
    velocity = Values.h;
    checkRep();
  }

  //获取编号
  public Integer getId() {
    return id;
  }

  /**
   * 判定是否上了梯子
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
   * 判定是否从左岸上了梯子
   *
   * @param monkey 不在梯子上且方向为从左至右的猴子
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
      System.out.println("monkey "+monkey.getId()+" 在岸边上了"+getId()+"号梯子的左端");
      GUI.getTextGUI().addText("monkey "+monkey.getId()+" 在岸边上了"+getId()+"号梯子的左端");
      direction = "L->R";
      if (monkey.getVelocity()<velocity) velocity = monkey.getVelocity();
      return true;
    }
    return false;
  }

  /**
   * 判定是否从右岸上了梯子
   *
   * @param monkey 不在梯子上的猴子
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
      System.out.println("monkey "+monkey.getId()+" 在岸边上了"+getId()+"号梯子的右端");
      GUI.getTextGUI().addText("monkey "+monkey.getId()+" 在岸边上了"+getId()+"号梯子的右端");
      direction = "R->L";
      if (monkey.getVelocity()<velocity) velocity = monkey.getVelocity();
      return true;
    }
    return false;
  }

  /**
   * 移动.
   *
   * @param monkey 梯子上的猴子.
   * @return 猴子下梯子返回true,反之false.
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
        recordLog("monkey "+monkey.getId()+"在"+id+"梯子"+(index + monkey.getVelocity()+1)+"踏板处右移,已经出生"
            +(System.currentTimeMillis()-monkey.getBirthTime())/1000
            +"秒");
        System.out.println("monkey "+monkey.getId()+"在"+id+"梯子右移");
        GUI.getTextGUI().addText("monkey "+monkey.getId()+"在"+id+"梯子右移");
        if (map.get(index + velocity).isPresent()) {
          System.out.println("monkey "+monkey.getId()+"在"+id+"梯子死锁");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+"在"+id+"梯子死锁");
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
          System.out.println("monkey "+monkey.getId()+" 下了"+id+"号梯子,成功");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+" 下了"+id+"号梯子,成功");
          recordLog("monkey "+monkey.getId()+"在"+id+"梯子,成功下梯子!"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"秒");
          lock.unlock();
          return true;
        } else {
          // monkey move itself velocity
          // move monkey
          lock.lock();
          map.put(index, Optional.empty());
          System.out.println("monkey "+monkey.getId()+"在"+id+"梯子右移");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+"在"+id+"梯子右移");
          if (map.get(index + monkey.getVelocity()).isPresent()) {
            System.out.println("monkey "+monkey.getId()+"在"+id+"梯子死锁");
            GUI.getTextGUI().addText("monkey "+monkey.getId()+"在"+id+"梯子死锁");
            lock.unlock();
            return false;
          }
          map.put(index + monkey.getVelocity(), Optional.of(monkey));
          recordLog("monkey "+monkey.getId()+"在"+id+"梯子"+(index + monkey.getVelocity()+1)+"板上右移,已经出生"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"秒");
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
          System.out.println("monkey "+monkey.getId() +"在"+id+"桥上死锁");
          GUI.getTextGUI().addText("monkey "+monkey.getId() +"在"+id+"桥上死锁");
          lock.unlock();
          return false;
        }
        System.out.println("monkey "+monkey.getId()+"在"+id+"桥上左移");
        GUI.getTextGUI().addText("monkey "+monkey.getId()+"在"+id+"桥上左移");
        map.put(index - velocity, Optional.of(monkey));
        recordLog("monkey "+monkey.getId()+"在"+id+"桥上"+(index + monkey.getVelocity()+1)+"踏板处左移,已经出生"
            +(System.currentTimeMillis()-monkey.getBirthTime())/1000
            +"秒");
        lock.unlock();
      } else {
        // no monkey before you
        if (-monkey.getVelocity() + index <0) {
          // monkey can go down the bridge
          // move monkey
          lock.lock();
          map.put(index, Optional.empty());
          System.out.println("monkey "+monkey.getId()+" 下了"+getId()+"号梯子,成功");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+" 下了"+getId()+"号梯子,成功");
          recordLog("monkey "+monkey.getId()+"在"+id+"梯子上,成功下梯子!耗时"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"秒");
          lock.unlock();
          return true;
        } else {
          // monkey move itself velocity
          // move monkey
          lock.lock();
          map.put(index, Optional.empty());
          if (map.get(index - monkey.getVelocity()).isPresent()) {
            System.out.println("monkey "+monkey.getId()+"在"+id+"梯子死锁");
            GUI.getTextGUI().addText("monkey "+monkey.getId()+"在"+id+"梯子死锁");
            lock.unlock();
            return false;
          }
          System.out.println("monkey "+monkey.getId()+"在"+id+"梯子左行");
          GUI.getTextGUI().addText("monkey "+monkey.getId()+"在"+id+"梯子死锁");
          map.put(index - monkey.getVelocity(), Optional.of(monkey));
          recordLog("monkey "+monkey.getId()+"在"+id+"梯子"+(index + monkey.getVelocity()+1)+"踏板处左移,已经出生"
              +(System.currentTimeMillis()-monkey.getBirthTime())/1000
              +"秒");
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
   * 检查猴子是否在梯子上
   *
   * @param monkey 被检查的猴子
   * @return 在就返回true
   */
  private boolean isOnLadder(Monkey monkey){
    checkRep();
    return map.containsValue(Optional.of(monkey));
  }

  /**
   * 检查梯子是否是空的
   *
   * @return 是返回true
   */
  public synchronized boolean isEmpty(){
      for (Map.Entry entry:map.entrySet()){
        if (!entry.getValue().equals(Optional.empty())) return false;
      }
      return true;
  }

  /**
   * 检查左岸是否没有猴子
   *
   * @return 是返回true
   */
  public synchronized boolean isLeftEmpty(){
      if (map.get(0).isPresent()) return false;
      return true;
  }

  /**
   * 检查右岸是否没有猴子
   *
   * @return 是返回true
   */
  public synchronized boolean isRightEmpty(){
    if (map.get(map.size()-1).isPresent()) return false;
    return true;
  }

  /**
   * 获取方向.
   *
   * @return "L->R" or "R->L"
   */
  public String getDirection(){
    return direction;
  }

  /**
   * 获取最慢猴子速度.
   *
   * @return the slowest monkey's velocity.
   */
  public Integer getVelocity(){
    return velocity;
  }

  /**
   * 记录日志.
   *
   * @param message 要记录的信息
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
