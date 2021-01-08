package monkeyCrossRiverSimulator;
import java.util.ArrayList;
import java.util.List;

import properties.Values;

public class Bridges {

  //fields
  private final List<Ladder> ladders = new ArrayList<>();
  private static final Bridges bridges = new Bridges();

  //Abstract Function
  // AF
  //Rep Invariant
  // ladders.size() == Values.n
  //Safe From Rep Exposure
  //


  /**
   * 获取唯一的bridges，即代表所有梯子.
   *
   * @return the bridges
   */
  public static Bridges getBridges(){
    return bridges;
  }

  //initialize the bridges
  private Bridges() {
    //initialize the bridges.
    try{
      for (int  i = 0;i<Values.n;i++){
        ladders.add(new Ladder(i));
      }
    }catch (Exception e){
      e.printStackTrace();
    }


  }

  /**
   * 获取某个梯子.
   *
   * @param index should >=0 and < bridges.size()
   * @return the ladder of the index
   */
  public Ladder get(int index){
    //check index
    assert index>=0 && index< size();
    return ladders.get(index);
  }

  /**
   * 获取梯子数.
   *
   * @return the size
   */
  public int size(){
    return ladders.size();
  }

  //check Rep
  private void checkRep(){
    assert ladders.size()==Values.n;
  }

}
