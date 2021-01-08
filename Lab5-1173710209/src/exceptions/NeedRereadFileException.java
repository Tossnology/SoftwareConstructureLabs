package exceptions;

public class NeedRereadFileException extends Exception {
  /**
   * 需要重新读取文件.
   */
  private static final long serialVersionUID = 1L;

  public NeedRereadFileException(String msg) {
    super(msg);
  }
}
