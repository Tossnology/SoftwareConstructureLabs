package strategy;

import monkeyCrossRiverSimulator.Monkey;

/**
 * ²ßÂÔ½Ó¿Ú
 */
public interface Strategy {

  /**
   * the method to do decision.
   * Tell the monkey choose which ladder(bridge).
   *
   * @param monkey the monkey needs decision
   * @return -1 if no deicison or the number of the bridge(ladder)
   */
  public int decide(Monkey monkey);

}
