package exceptions;

public class NeedRereadFileException extends Exception {
  /**
   * ��Ҫ���¶�ȡ�ļ�.
   */
  private static final long serialVersionUID = 1L;

  public NeedRereadFileException(String msg) {
    super(msg);
  }
}
