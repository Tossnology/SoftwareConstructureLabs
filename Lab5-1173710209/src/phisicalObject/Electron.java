package phisicalObject;

/**
 * ����.
 * immutable
 * @author yRXX
 *
 */
public class Electron implements PhisicalObject {

  private final String name = "electron";

  /*
   * Abstraction function:
   *   AF(name)=һ������
   * Representation invariant:
   *   name:ͳһΪelectron
   * Safety from rep exposure:
   *   �������Զ���private
   */

  @Override
  public String getName() {
    return name;
  }
}
