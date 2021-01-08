package phisicalObject;

/**
 * 行星 immutable
 * 
 * @author yRXX
 *
 */
public class Planet implements PhisicalObject {
  private String name;
  private String state;
  private String color;
  private double radius;
  private double orbitRadius;
  private double speed;
  private String direction;
  private double initAngle;

  /*
   * Abstraction function:
   * AF(name,state,color,radius,orbitRadius,speed,direction,initAngle)=一个行星
   * Representation invariant: name:行星名字，类型为label state:形态，只能为Solid或Liquid或Gas
   * color:颜色，类型为label radius:行星半径，大于0 orbitRadius:轨道半径，大于0 speed:公转速度，大于0
   * direction:公转方向，只能为CW(顺)或CCW(逆) initAngle:初始角度，[0,360) Safety from rep
   * exposure: 所有属性都是private
   */

  /**
   * 检查rep.
   */
  public void checkRep() {
    assert name != null && name.matches("^[A-Za-z0-9]+$") : "名字必须为label类型";
    assert state.equals("Solid") || state.equals("Liquid") || state.equals("Gas") : "只能为Solid或Liquid或Gas";
    assert color != null && color.matches("^[A-Za-z0-9]+$") : "颜色必须为label类型";
    assert radius > 0 : "半径必须大于0";
    assert orbitRadius > 0 : "轨道半径必须大于0";
    assert speed > 0 : "公转速度必须大于0";
    assert direction.equals("CW") || direction.equals("CCW") : "只能为CW(顺)或CCW(逆)";
    assert initAngle >= 0 && initAngle < 360 : "初始角度范围:[0,360)";
  }

  /**
   * 行星构造器.
   * 
   * @param name
   *          名字
   * @param state
   *          形态
   * @param color
   *          颜色
   * @param radius
   *          半径
   * @param orbitRadius
   *          轨道半径
   * @param speed
   *          公转速度
   * @param direction
   *          公转方向
   * @param initAngle
   *          初始角度
   */
  public Planet(String name, String state, String color, double radius, double orbitRadius, double speed,
      String direction, double initAngle) {
    this.name = name;
    this.state = state;
    this.color = color;
    this.radius = radius;
    this.orbitRadius = orbitRadius;
    this.speed = speed;
    this.direction = direction;
    this.initAngle = initAngle;
    checkRep();
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * 获取形态.
   * 
   * @return 行星形态
   */
  public String getState() {
    return state;
  }

  /**
   * 获取颜色.
   * 
   * @return 行星颜色
   */
  public String getColor() {
    return color;
  }

  /**
   * 获取半径.
   * 
   * @return 行星半径
   */
  public double getRadius() {
    return radius;
  }

  /**
   * 获取轨道半径.
   * 
   * @return 轨道半径
   */
  public double getOrbitRadius() {
    return orbitRadius;
  }

  /**
   * 获取公转速度.
   * 
   * @return 公转速度
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * 获取公转方向.
   * 
   * @return 公转方向
   */
  public String getDirection() {
    return direction;
  }

  /**
   * 获取初始角度.
   * 
   * @return 初始角度
   */
  public double getInitAngle() {
    return initAngle;
  }
}
