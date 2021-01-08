package centralObject;

/**
 * ԭ�Ӻ� immutable.
 * 
 * @author yRXX
 *
 */
public class Nucleus {
  private String name;

  /*
   * Abstraction function: AF(name,contents)=һ��ԭ�Ӻ� 
   * Representation invariant:
   *   name:Ԫ������ contents:ԭ�Ӻ��е����壬���������Ӻ����� 
   * Safety from rep exposure: �������Զ���private
   */

  /**
   * ����һ���յ�ԭ�Ӻ�.
   */
  public Nucleus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
