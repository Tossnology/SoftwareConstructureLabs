package strategy;
import java.util.Random;
import javax.swing.text.AbstractDocument.BranchElement;

import monkeyCrossRiverSimulator.Bridges;
import monkeyCrossRiverSimulator.Ladder;
import monkeyCrossRiverSimulator.Monkey;
import properties.Values;

/**
 * 目前最好的策略.
 */
public class BestStrategy implements Strategy {

  @Override
  public int decide(Monkey monkey) {
    //按速度分高速组和低数组,优先选没猴子的组,然后是同组同向的组,然后等待.
    int medianVelocity = Values.MV/2;
    Bridges bridges = Bridges.getBridges();
    boolean hasHighLadder = false;

    //如果有空选空的桥.
    for (int i =0;i<bridges.size();i++){
      Ladder ladder = bridges.get(i);
      if (ladder.isEmpty()) return i;
    }
    //如果没空选同向同组入口空的桥
    for (int i =0;i<bridges.size();i++){
      Ladder ladder = bridges.get(i);
      if (ladder.isEmpty()||ladder.getDirection().equals(monkey.getDirection())
          &&isSameGroup(monkey,ladder,medianVelocity)){
        return i;
      }
    }
    return -1;


  }

  private boolean isSameGroup(Monkey monkey,Ladder ladder,Integer median){
    boolean monkeyGroup = monkey.getVelocity()>=median;
    boolean ladderGroup = ladder.getVelocity()>=median;
    if (monkeyGroup==ladderGroup) return true;
    return false;
  }
}