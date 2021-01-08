package centralObject;

/**
 * 原子核 immutable.
 * 
 * @author yRXX
 *
 */
public class Nucleus {
  private String name;

  /*
   * Abstraction function: AF(name,contents)=一个原子核 
   * Representation invariant:
   *   name:元素名字 contents:原子核中的物体，可以是中子和质子 
   * Safety from rep exposure: 所有属性都是private
   */

  /**
   * 构造一个空的原子核.
   */
  public Nucleus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
