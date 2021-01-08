package centralObject;

/**
 * ���� immutable.
 * 
 * @author yRXX
 *
 */
public class Stellar {
  private String name;
  private double radius;
  private double mass;

  /*
   * Abstraction function: AF(name,radius,mass)=һ������ 
   * Representation invariant:name:���ǵ����֣�����Ϊ��������Ϊlabel radius:���ǵİ뾶������0 mass:���ǵ�����������0 
   * Safety from rep exposure: �������Զ���private
   */

  /**
   * ���rep.
   */
  public void checkRep() {
    assert name != null && name.matches("^[A-Za-z0-9]+$") : "���Ʊ���Ϊlabel����";
    assert radius > 0 : "�뾶�������0";
    assert mass > 0 : "�����������0";
  }

  /**
   * ����һ�����Ƕ���.
   * 
   * @param name
   *          ���ǵ�����
   * @param radius
   *          ���ǵİ뾶
   * @param mass
   *          ���ǵ�����
   */
  public Stellar(String name, double radius, double mass) {
    this.name = name;
    this.radius = radius;
    this.mass = mass;
  }

  /**
   * ��ȡ��������.
   * 
   * @return ��������
   */
  public String getName() {
    return name;
  }

  /**
   * ��ȡ���ǰ뾶.
   * 
   * @return ���ǰ뾶
   */
  public double getRadius() {
    return radius;
  }

  /**
   * ��ȡ��������.
   * 
   * @return ��������
   */
  public double getMass() {
    return mass;
  }
}
