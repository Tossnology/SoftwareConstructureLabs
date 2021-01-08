package APIs;

import java.util.ArrayList;
import circularOrbit.CircularOrbit;
import phisicalObject.PhisicalObject;

/**
 * �������ϵͳ֮��Ĳ���.
 * 
 * @author yRXX
 *
 */
public class Difference {
  private ArrayList<String> list = new ArrayList<String>();
  private ArrayList<Integer> a1 = new ArrayList<Integer>();
  private ArrayList<Integer> a2 = new ArrayList<Integer>();

  /**
   * ��ͬ.
   * @param c1 ���ϵͳ1
   * @param c2 ���ϵͳ2
   */
  public <L, E extends PhisicalObject> Difference(CircularOrbit<L, E> c1, CircularOrbit<L, E> c2) {
    list.add("���������:" + (c1.getTracks().size() - c2.getTracks().size()));

    if (c1.getTracks().size() > c2.getTracks().size()) {
      for (int i = 0; i < c1.getTracks().size(); i++) {
        int count1 = 0;
        for (E t : c1.getTrackObjects().keySet()) {
          if (c1.getTrackObjects().get(t).equals(c1.getTracks().get(i))) {
            count1++;
          }

        }
        a1.add(count1);

        int count2 = 0;
        if (i < c2.getTracks().size()) {
          for (E t : c2.getTrackObjects().keySet()) {
            if (c2.getTrackObjects().get(t).equals(c2.getTracks().get(i))) {
              count2++;
            }

          }
          a2.add(count2);
        } else {
          a2.add(0);
        }
      }
    } else {
      for (int i = 0; i < c2.getTracks().size(); i++) {
        int count2 = 0;
        for (E t : c2.getTrackObjects().keySet()) {
          if (c2.getTrackObjects().get(t).equals(c2.getTracks().get(i))) {
            count2++;
          }

        }
        a2.add(count2);

        int count1 = 0;
        if (i < c1.getTracks().size()) {
          for (E t : c1.getTrackObjects().keySet()) {
            if (c1.getTrackObjects().get(t).equals(c1.getTracks().get(i))) {
              count1++;
            }

          }
          a1.add(count1);
        } else {
          a1.add(0);
        }
      }
    }

    for (int i = 0; i < a1.size(); i++) {
      list.add("���" + (i + 1) + "��������������Ϊ:" + (a1.get(i) - a2.get(i)));
    }
  }
}