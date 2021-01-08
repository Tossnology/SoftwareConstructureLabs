package exceptions;

public class CentralPersonException extends Exception {
  /**
   * 中心点异常.
   */
  private static final long serialVersionUID = 1L;

  public CentralPersonException(String msg) {
    super(msg);
  }
}
