package centralObject;

/**
 * 恒星 immutable.
 * 
 * @author yRXX
 *
 */
public class Stellar {
  private String name;
  private double radius;
  private double mass;

  /*
   * Abstraction function: AF(name,radius,mass)=一个恒星 
   * Representation invariant:name:恒星的名字，不能为空且类型为label radius:恒星的半径，大于0 mass:恒星的质量，大于0 
   * Safety from rep exposure: 所有属性都是private
   */

  /**
   * 检查rep.
   */
  public void checkRep() {
    assert name != null && name.matches("^[A-Za-z0-9]+$") : "名称必须为label类型";
    assert radius > 0 : "半径必须大于0";
    assert mass > 0 : "质量必须大于0";
  }

  /**
   * 构造一个恒星对象.
   * 
   * @param name
   *          恒星的名字
   * @param radius
   *          恒星的半径
   * @param mass
   *          恒星的质量
   */
  public Stellar(String name, double radius, double mass) {
    this.name = name;
    this.radius = radius;
    this.mass = mass;
  }

  /**
   * 获取恒星名字.
   * 
   * @return 恒星名字
   */
  public String getName() {
    return name;
  }

  /**
   * 获取恒星半径.
   * 
   * @return 恒星半径
   */
  public double getRadius() {
    return radius;
  }

  /**
   * 获取恒星质量.
   * 
   * @return 恒星质量
   */
  public double getMass() {
    return mass;
  }
}
