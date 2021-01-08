package phisicalObject;

/**
 * �� immutable.
 * 
 * @author yRXX
 *
 */
public class Person implements PhisicalObject {
  private String name;
  private int age;
  private String sex;

  /*
   * Abstraction function: AF(name,age,sex)=һ���� Representation invariant:
   * name:�������ǿգ�����Ϊlabel age:���䣬������ sex:�Ա�M��F Safety from rep exposure:
   * �������Զ���private
   */

  /**
   * ���rep.
   */
  public void checkRep() {
    assert name != null && name.matches("^[A-Za-z0-9]+$") : "��������Ϊlabel����";
    assert age > 0 : "����������0";
    assert sex.equals("M") || sex.equals("F") : "�Ա�ֻ��ΪM��F";
  }

  /**
   * ���ݲ���������Ӧ���˶���.
   * 
   * @param name
   *          ����
   * @param age
   *          ����
   * @param sex
   *          �Ա�
   */
  public Person(String name, int age, String sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * ��ȡ����.
   * 
   * @return ����
   */
  public int getAge() {
    return age;
  }

  /**
   * ��ȡ�Ա�.
   * 
   * @return �Ա�
   */
  public String getSex() {
    return sex;
  }
}
