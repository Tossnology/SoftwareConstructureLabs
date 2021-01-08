package strategy;

import monkeyCrossRiverSimulator.Bridges;
import monkeyCrossRiverSimulator.Ladder;
import monkeyCrossRiverSimulator.Monkey;

/**
 * decision maker choose the same direction the fastest ladder.
 */
public class SameDirectionStrategy implements Strategy {

  @Override
  public int decide(Monkey monkey) {

    Bridges bridges = Bridges.getBridges();
    for (int i = 0;i<bridges.size();i++){
      Ladder ladder = bridges.get(i);
      if (ladder.isEmpty()||ladder.getDirection().equals(monkey.getDirection())){
        return i;
      }
    }

    return -1;
  }
}
