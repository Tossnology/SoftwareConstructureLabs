package strategy;

import monkeyCrossRiverSimulator.Bridges;
import monkeyCrossRiverSimulator.Ladder;
import monkeyCrossRiverSimulator.Monkey;

/**
 * test strategy to help monkey to pass the river.
 */
public class TestStrategy implements Strategy {

  //choose a ladder without monkey.
  @Override
  public int decide(Monkey monkey) {
    Bridges bridges = Bridges.getBridges();

    //find empty bridge and tell monkey
    for (int i =0;i<bridges.size();i++){
      Ladder ladder = bridges.get(i);
      if (ladder.isEmpty()){
        //get on the ladder
        return i;
      }
    }
    //not bridge so wait
    return -1;
  }
}
