package strategy;
import java.util.Random;
import javax.swing.text.AbstractDocument.BranchElement;

import monkeyCrossRiverSimulator.Bridges;
import monkeyCrossRiverSimulator.Ladder;
import monkeyCrossRiverSimulator.Monkey;
import properties.Values;

/**
 * Ŀǰ��õĲ���.
 */
public class BestStrategy implements Strategy {

  @Override
  public int decide(Monkey monkey) {
    //���ٶȷָ�����͵�����,����ѡû���ӵ���,Ȼ����ͬ��ͬ�����,Ȼ��ȴ�.
    int medianVelocity = Values.MV/2;
    Bridges bridges = Bridges.getBridges();
    boolean hasHighLadder = false;

    //����п�ѡ�յ���.
    for (int i =0;i<bridges.size();i++){
      Ladder ladder = bridges.get(i);
      if (ladder.isEmpty()) return i;
    }
    //���û��ѡͬ��ͬ����ڿյ���
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