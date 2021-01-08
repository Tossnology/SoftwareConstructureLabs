package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import circularOrbit.CircularOrbit;
import graph.Graph;
import javafx.application.Application;
import javafx.stage.Stage;
import phisicalObject.Person;
import phisicalObject.Planet;
import track.Track;

public class SocialNetworkCircle  extends Application{
	
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		
		ReadFile(new File("src/txts/SocialNetworkCircle.txt"));
		index(args);
	}

	private final static CircularOrbit<Person, Person> Circle = CircularOrbit.getEmpty();
	private final static Graph<String> graph = Graph.empty();

	/**
	 * 计算信息扩散度,定义如果亲密度>0.5则有效
	 * 
	 * @param p
	 *            目标用户
	 * @return 该用户的信息扩散度
	 */
	public static int diffusivity(String p) {
		int result = 0;
		for (String s : graph.targets(p).keySet()) {
			if (graph.targets(p).get(s) > 0.5) {
				result++;
			}
		}
		return result;
	}

	/**
	 * 添加社交关系
	 * 
	 * @param p1
	 *            用户1姓名
	 * @param p2
	 *            用户2姓名
	 * @param weight
	 *            亲密度
	 */
	public static void addRelation(String p1, String p2, double weight) {
		graph.set(p1, p2, weight);
		graph.set(p2, p1, weight);
	}

	/**
	 * 删除一条社交关系
	 * 
	 * @param p1
	 *            用户1姓名
	 * @param p2
	 *            用户2姓名
	 */
	public static void removeRelation(String p1, String p2) {
		graph.set(p1, p2, 0);
		graph.set(p2, p1, 0);
	}

	/**
	 * 调整位置
	 */
	public static void adjust() {
		Queue<String> q = new LinkedList<String>();
		Map<String, Integer> m = new HashMap<String, Integer>();
		for (String p : graph.vertices()) {
			if (p.equals(Circle.getCentralObjects().get(0).getName())) {
				m.put(p, 0);
				q.add(p);
			} else {
				m.put(p, -1);
			}
		}
		while (!q.isEmpty()) {
			String p1 = q.poll();
			for (String p2 : graph.targets(p1).keySet()) {
				if (m.get(p2) == -1) {
					m.put(p2, m.get(p1) + 1);
					Circle.addTrack(new Track(m.get(p2)));
					for (Person p : Circle.getTrackObjects().keySet()) {
						if (p.getName().equals(p2)) {
							Circle.removeTrackObject(p.getName());
							Circle.addTrackObject(p, new Track(m.get(p2)));
							break;
						}
					}
					q.add(p2);
				}
			}
		}
		
		Circle.sortTracks();
	}

	/**
	 * 从文件读取数据
	 * 
	 * @param filename
	 *            文件路径
	 */
	public static void ReadFile(File filename) {
		String line;
		StringBuilder str = new StringBuilder();

		Pattern CentralUser = Pattern.compile("CentralUser ::= <([A-Za-z0-9]+),([0-9]+),(M|F)>");
		Pattern SocialTie = Pattern.compile("SocialTie ::= <([A-Za-z0-9]+),([A-Za-z0-9]+),(0.(\\d)+)>");
		Pattern Friend = Pattern.compile("Friend ::= <([A-Za-z0-9]+),([0-9]+),(M|F)>");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {

			while ((line = in.readLine()) != null) {
				str.append(line + "\n");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Matcher m1 = CentralUser.matcher(str);
		Matcher m2 = SocialTie.matcher(str);
		Matcher m3 = Friend.matcher(str);

		while (m1.find()) {
			Circle.addCentralObject(new Person(m1.group(1), Integer.valueOf(m1.group(2)), m1.group(3)));
		}

		while (m2.find()) {
			addRelation(m2.group(1), m2.group(2), Double.valueOf(m2.group(3)));
		}

		while (m3.find()) {
			Circle.addTrackObject(new Person(m3.group(1), Integer.valueOf(m3.group(2)), m3.group(3)), new Track(-1));
		}

		adjust();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		CircularOrbitHelper.visualizeSocialNetworkCircle(Circle, graph, primaryStage);
	}
	
	/**
	 * 主界面
	 * @param args main的参数
	 */
	public static void index(String[] args) {
		while (true) {
			System.out.println("1.增加新轨道");
			System.out.println("2.增加轨道物体");
			System.out.println("3.删除轨道");
			System.out.println("4.删除轨道物体");
			System.out.println("5.计算熵值");
			System.out.println("6.计算信息扩散度");
			System.out.println("7.增加社交关系");
			System.out.println("8.删除社交关系");
			System.out.println("9.计算两用户间逻辑距离");
			System.out.println("10.可视化朋友圈");
			System.out.println("输入end退出");

			int choice;

			while (true) {
				System.out.println("请输入:");
				String s = in.nextLine();
				if (s.matches("[1-9]|10")) {
					choice = Integer.valueOf(s);
					break;
				} else if (s.equals("end")) {
					System.out.println("谢谢使用");
					System.exit(0);
				} else {
					System.out.println("非法输入(请输入1,2,3,4,end)");
				}
			}

			switch (choice) {
			case 1: {
				double radius = 0;

				while (true) {
					System.out.println("请输入新半径:");
					String s = in.nextLine();
					System.out.println(s);

					Pattern number = Pattern.compile("[0-9]+.*\\d*e*[0-9]*");
					Matcher m = number.matcher(s);
					if (m.find()) {
						radius = Double.valueOf(m.group());
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入(请输入number类型或end)");
					}
				}
				Circle.addTrack(new Track(radius));
				break;
			}

			case 2: {

				while (true) {
					System.out.println("请输入新用户信息:");
					Pattern Person = Pattern.compile(
							"([A-Za-z0-9]+),([0-9]+),(M|F)");
					String s = in.nextLine();
					Matcher m = Person.matcher(s);
					if (m.find()) {
						Person p = new Person(m.group(1),Integer.valueOf(m.group(2)),m.group(3));
						Circle.addTrackObject(p, Circle.getTracks().get(Circle.getTracks().size()-1));
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入");
					}
				}
				break;
			}

			case 3: {
				int index;

				while (true) {
					System.out.println("请输入要删除的轨道号:");
					String s = in.nextLine();
					if (s.matches("[0-9]+")) {
						index = Integer.valueOf(s)-1;
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入");
					}
				}
				Circle.getTracks().remove(index);
				break;
			}

			case 4: {

				while (true) {
					System.out.println("请输入要删除的物体:");
					String s = in.nextLine();
					if (s.matches("[A-Za-z0-9]+")) {
						Circle.removeTrackObject(s);
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入");
					}
				}
				break;
			}

			case 5: {
				CircularOrbitAPIs<Planet> a = new CircularOrbitAPIs<Planet>();
				System.out.println(a.getObjectDistributionEntropy(Circle));
				break;
			}

			case 6: {

				while (true) {
					System.out.println("请输入用户名字");
					Pattern name = Pattern.compile(
							"[A-Za-z0-9]+");
					String s = in.nextLine();
					Matcher m = name.matcher(s);
					if (m.find()) {
						System.out.println(diffusivity(m.group()));
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入");
					}
				}
				break;
			}

			case 7: {
				
				while (true) {
					System.out.println("请输入用户1,用户2,亲密度");
					Pattern socialtie = Pattern.compile(
							"([A-Za-z0-9]+),([A-Za-z0-9]+),((0.(\\d)+))");
					String s = in.nextLine();
					Matcher m = socialtie.matcher(s);
					if (m.find()) {
						addRelation(m.group(1),m.group(2), Double.valueOf(m.group(3)));
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入");
					}
				}
				break;
			}
			
			case 8: {
				
				while (true) {
					System.out.println("请输入用户1,用户2");
					Pattern socialtie = Pattern.compile(
							"([A-Za-z0-9]+),([A-Za-z0-9]+)");
					String s = in.nextLine();
					Matcher m = socialtie.matcher(s);
					if (m.find()) {
						removeRelation(m.group(1),m.group(2));
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入");
					}
				}
				break;
			}
			
			case 9: {
				while (true) {
					System.out.println("请输入用户1,用户2");
					Pattern socialtie = Pattern.compile(
							"([A-Za-z0-9]+),([A-Za-z0-9]+)");
					String s = in.nextLine();
					Matcher m = socialtie.matcher(s);
					if (m.find()) {
						System.out.println(CircularOrbitAPIs.getLogicalDistance(graph, m.group(1), m.group(2)));
						break;
					} else if (s.equals("end")) {
						System.out.println("谢谢使用");
						System.exit(0);
					} else {
						System.out.println("非法输入");
					}
				}
				break;
			}
			
			case 10:{
				break;
			}
			}
			
			if(choice==10) {
				break;
			}
		}
		
		launch(args);
	}
}
