package phisicalObject;

/**
 * ���� immutable
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
   * AF(name,state,color,radius,orbitRadius,speed,direction,initAngle)=һ������
   * Representation invariant: name:�������֣�����Ϊlabel state:��̬��ֻ��ΪSolid��Liquid��Gas
   * color:��ɫ������Ϊlabel radius:���ǰ뾶������0 orbitRadius:����뾶������0 speed:��ת�ٶȣ�����0
   * direction:��ת����ֻ��ΪCW(˳)��CCW(��) initAngle:��ʼ�Ƕȣ�[0,360) Safety from rep
   * exposure: �������Զ���private
   */

  /**
   * ���rep.
   */
  public void checkRep() {
    assert name != null && name.matches("^[A-Za-z0-9]+$") : "���ֱ���Ϊlabel����";
    assert state.equals("Solid") || state.equals("Liquid") || state.equals("Gas") : "ֻ��ΪSolid��Liquid��Gas";
    assert color != null && color.matches("^[A-Za-z0-9]+$") : "��ɫ����Ϊlabel����";
    assert radius > 0 : "�뾶�������0";
    assert orbitRadius > 0 : "����뾶�������0";
    assert speed > 0 : "��ת�ٶȱ������0";
    assert direction.equals("CW") || direction.equals("CCW") : "ֻ��ΪCW(˳)��CCW(��)";
    assert initAngle >= 0 && initAngle < 360 : "��ʼ�Ƕȷ�Χ:[0,360)";
  }

  /**
   * ���ǹ�����.
   * 
   * @param name
   *          ����
   * @param state
   *          ��̬
   * @param color
   *          ��ɫ
   * @param radius
   *          �뾶
   * @param orbitRadius
   *          ����뾶
   * @param speed
   *          ��ת�ٶ�
   * @param direction
   *          ��ת����
   * @param initAngle
   *          ��ʼ�Ƕ�
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
   * ��ȡ��̬.
   * 
   * @return ������̬
   */
  public String getState() {
    return state;
  }

  /**
   * ��ȡ��ɫ.
   * 
   * @return ������ɫ
   */
  public String getColor() {
    return color;
  }

  /**
   * ��ȡ�뾶.
   * 
   * @return ���ǰ뾶
   */
  public double getRadius() {
    return radius;
  }

  /**
   * ��ȡ����뾶.
   * 
   * @return ����뾶
   */
  public double getOrbitRadius() {
    return orbitRadius;
  }

  /**
   * ��ȡ��ת�ٶ�.
   * 
   * @return ��ת�ٶ�
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * ��ȡ��ת����.
   * 
   * @return ��ת����
   */
  public String getDirection() {
    return direction;
  }

  /**
   * ��ȡ��ʼ�Ƕ�.
   * 
   * @return ��ʼ�Ƕ�
   */
  public double getInitAngle() {
    return initAngle;
  }
}
