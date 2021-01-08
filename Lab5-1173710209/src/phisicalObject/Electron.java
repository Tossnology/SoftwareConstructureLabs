package phisicalObject;

/**
 * 电子.
 * immutable
 * @author yRXX
 *
 */
public class Electron implements PhisicalObject {

  private final String name = "electron";

  /*
   * Abstraction function:
   *   AF(name)=一个电子
   * Representation invariant:
   *   name:统一为electron
   * Safety from rep exposure:
   *   所有属性都是private
   */

  @Override
  public String getName() {
    return name;
  }
}
