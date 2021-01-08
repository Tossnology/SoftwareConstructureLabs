package phisicalObject;

/**
 * 人 immutable.
 * 
 * @author yRXX
 *
 */
public class Person implements PhisicalObject {
  private String name;
  private int age;
  private String sex;

  /*
   * Abstraction function: AF(name,age,sex)=一个人 Representation invariant:
   * name:人名，非空，类型为label age:年龄，正整数 sex:性别，M或F Safety from rep exposure:
   * 所有属性都是private
   */

  /**
   * 检查rep.
   */
  public void checkRep() {
    assert name != null && name.matches("^[A-Za-z0-9]+$") : "人名必须为label类型";
    assert age > 0 : "年龄必须大于0";
    assert sex.equals("M") || sex.equals("F") : "性别只能为M或F";
  }

  /**
   * 根据参数构造相应的人对象.
   * 
   * @param name
   *          人名
   * @param age
   *          年龄
   * @param sex
   *          性别
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
   * 获取年龄.
   * 
   * @return 年龄
   */
  public int getAge() {
    return age;
  }

  /**
   * 获取性别.
   * 
   * @return 性别
   */
  public String getSex() {
    return sex;
  }
}
