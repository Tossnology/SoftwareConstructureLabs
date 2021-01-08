package APIs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import circularOrbit.CircularOrbit;
import graph.Graph;
import phisicalObject.PhisicalObject;
/**
 * ���Լ���
 * @author yRXX
 * @param <E>
 *
 */
public class CircularOrbitAPIs<E> {
	
	/**
	 * ������ϵͳ����ֵ
	 * @param c ���ϵͳ
	 * @return ��ֵ
	 */
	@SuppressWarnings("hiding")
	public <L,E extends PhisicalObject> double getObjectDistributionEntropy(CircularOrbit<L,E> c) {
		List<Integer> list = new ArrayList<>();
        for (int i=0;i<c.getTracks().size();i++){
        	int count = 0;
            for(E t: c.getTrackObjects().keySet()) {
            	if(c.getTrackObjects().get(t).equals(c.getTracks().get(i))) {
            		count++;
            	}
            }
            list.add(count);
        }
        int sum = 0;//total number
        for (Integer i : list) {
            sum += i;
        }

        double result = 1;//the rep store final result and temp calculation
        for (Integer i : list) {
            result*=nCr(sum,i);
            sum-=i;
        }

        result = Math.log(result)/Math.log(2);
        return result;
	}
	
	// The Value Of nCr
    private int nCr(int n, int r) {
        if (r==0||n==r) return 1;
        return fact(n).divide(fact(r).multiply(fact(n - r))).intValue();
    }

    // Returns factorial of n
    private BigInteger fact(int n) {
        BigInteger res = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++)
            res = res.multiply(BigInteger.valueOf(i));
        return res;
    }
	
	/**
	 * �����߼�����
	 * @param g ��ϵͼ
	 * @param e1 ����1
	 * @param e2 ����2
	 * @return �����߼�����
	 */
	public static <E> int getLogicalDistance (Graph<E> g, E e1, E e2) {
		Queue<E> q = new LinkedList<E>();
		Map<E, Integer> m = new HashMap<E, Integer>();
		for (E p : g.vertices()) {
			if (p.equals(e1)) {
				m.put(p, 0);
				q.add(p);
			} else {
				m.put(p, -1);
			}
		}
		while (!q.isEmpty()) {
			E p1 = q.poll();
			if (p1.equals(e2)) {
				return m.get(p1);
			}
			for (E p2 : g.targets(p1).keySet()) {
				if (m.get(p2) == -1) {
					m.put(p2, m.get(p1) + 1);
					q.add(p2);
				}
			}
		}
		return -1;
	}
	
	/**
	 * ��ȡ�������ϵͳ֮��Ĳ���
	 * @param c1 ���ϵͳ1
	 * @param c2 ���ϵͳ2
	 * @return ���߼����
	 */
	public Difference getDifference(CircularOrbit c1, CircularOrbit c2) {
		Difference difference = new Difference(c1,c2);

        return difference;
	}
}
