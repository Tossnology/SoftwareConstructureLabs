package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import circularOrbit.CircularOrbit;
import exceptions.CentralPersonException;
import exceptions.NeedRereadFileException;
import exceptions.NotExistPersonException;
import exceptions.SameNameException;
import exceptions.SexException;
import exceptions.WrongFileTypeException;
import graph.Graph;
import javafx.application.Application;
import javafx.stage.Stage;
import phisicalObject.Person;
import phisicalObject.Planet;
import track.Track;

public class SocialNetworkCircle extends Application {

	private static Scanner in = new Scanner(System.in);
	static Logger log = Logger.getLogger("SocialNetworkCircle");
	static FileHandler fh=null;
	
	
	public static void main(String[] args) throws IOException {
		log.setUseParentHandlers(false);
		fh = new FileHandler("src/log/SocialLog.log",true);
		fh.setFormatter(new MyFormatter());
		log.addHandler(fh);
		
		while(true){
			try {
				System.out.println("�������ļ�·��(src/txts/...):");
				String fn = in.nextLine();
				log.info("*�����ļ�·��:"+fn);
				ReadFile(new File(fn));
				break;
			}catch(FileNotFoundException e) {
				log.info("δ�ҵ��ļ��������������ļ�·��");
				e.printStackTrace();
				continue;
			}catch(NeedRereadFileException e) {
				log.info(e.getMessage());
				e.printStackTrace();
				continue;
			}
		}
		index(args);
		fh.close();
	}

	private static CircularOrbit<Person, Person> Circle = CircularOrbit.getEmpty();
	private final static Graph<String> graph = Graph.empty();

	/**
	 * ������Ϣ��ɢ��,����������ܶ�>0.5����Ч
	 * 
	 * @param p
	 *            Ŀ���û�
	 * @return ���û�����Ϣ��ɢ��
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
	 * ����罻��ϵ
	 * 
	 * @param p1
	 *            �û�1����
	 * @param p2
	 *            �û�2����
	 * @param weight
	 *            ���ܶ�
	 */
	public static void addRelation(String p1, String p2, double weight) {
		graph.set(p1, p2, weight);
		graph.set(p2, p1, weight);
	}

	/**
	 * ɾ��һ���罻��ϵ
	 * 
	 * @param p1
	 *            �û�1����
	 * @param p2
	 *            �û�2����
	 */
	public static void removeRelation(String p1, String p2) {
		graph.set(p1, p2, 0);
		graph.set(p2, p1, 0);
	}

	/**
	 * ����λ��
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
	 * ���ļ���ȡ����
	 * 
	 * @param filename
	 *            �ļ�·��
	 * @throws IOException
	 * @throws NeedRereadFileException 
	 */
	public static void ReadFile(File filename) throws IOException, NeedRereadFileException {
		Circle = CircularOrbit.getEmpty();
		
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean reRead = false;

		String line;
		StringBuilder str = new StringBuilder();

		Pattern CentralUser = Pattern.compile("CentralUser ::= <([A-Za-z0-9]+),([0-9]+),(M|F)>");
		Pattern SocialTie = Pattern.compile("SocialTie ::= <([A-Za-z0-9]+),([A-Za-z0-9]+),(0.(\\d)+)>");
		Pattern Friend = Pattern.compile("Friend ::= <([A-Za-z0-9]+),([0-9]+),([A-Za-z0-9]+)>");

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

		while ((line = in.readLine()) != null) {
			str.append(line + "\n");
		}

		Matcher m1 = CentralUser.matcher(str);
		Matcher m3 = Friend.matcher(str);

		ArrayList<String> friends = new ArrayList<String>();
		
		while (m1.find()) {
			flag1 = true;
			try {
				if (Circle.getCentralObjects().size() == 1) {
					throw new CentralPersonException("���ĵ�ֻ����һ����");
				}
				Circle.addCentralObject(new Person(m1.group(1), Integer.valueOf(m1.group(2)), m1.group(3)));
				friends.add(m1.group(1));
			} catch (CentralPersonException e) {
				log.info("�ļ���ʽ�������ĵ�ֻ����һ����");
				e.printStackTrace();
				reRead = true;
			}
		}

		while (m3.find()) {
			flag3 = true;
			try {
				if (friends.contains(m3.group(1))) {
					throw new SameNameException("������ͬһ����" + m3.group(1));
				}

				if (!m3.group(3).equals("M") && !m3.group(3).equals("F")) {
					throw new SexException("�Ա�ֻ��ΪM/F,����Ϊ" + m3.group(3));
				}

				Circle.addTrackObject(new Person(m3.group(1), Integer.valueOf(m3.group(2)), m3.group(3)),
						new Track(-1));
				friends.add(m3.group(1));
			} catch (SameNameException e) {
				log.info("�ļ���ʽ���󣬲�����ͬһ����");
				e.printStackTrace();
				reRead = true;
			} catch (SexException e) {
				log.info("�ļ���ʽ����"+e.getMessage());
				e.printStackTrace();
				reRead = true;
			}
		}

		Matcher m2 = SocialTie.matcher(str);
		
		while (m2.find()) {
			flag2 = true;
			try {
				if (!(friends.contains(m2.group(1)) && friends.contains(m2.group(2)))) {
					throw new NotExistPersonException("<"+m2.group(1)+","+m2.group(2)+","+m2.group(3)+ ">�ù�ϵ�д��ڷǷ��û�");
				}
				addRelation(m2.group(1), m2.group(2), Double.valueOf(m2.group(3)));
			} catch (NotExistPersonException e) {
				log.info("�ļ���ʽ����"+e.getMessage());
				e.printStackTrace();
				reRead = true;
			}
		}

		try {
			if ((flag1 && flag2 && flag3) == false) {
				WrongFileTypeException e = new WrongFileTypeException("�ļ����Ͳ��ԣ�������SocialNetworkCircle");
				throw e;
			}
		} catch (WrongFileTypeException e) {
			log.info(e.getMessage());
			e.printStackTrace();
			reRead = true;
		}
		
		if(reRead == true) {
			throw new NeedRereadFileException("�ļ������⣬��Ҫ���¶�ȡ�ļ�");
		}
		
		adjust();
		
		log.info("*�ѳɹ���ȡ�ļ�"+filename.getPath());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		CircularOrbitHelper.visualizeSocialNetworkCircle(Circle, graph, primaryStage);
	}

	/**
	 * ������
	 * 
	 * @param args
	 *            main�Ĳ���
	 * @throws IOException 
	 */
	public static void index(String[] args) throws IOException {
		while (true) {
			System.out.println("1.�����¹��");
			System.out.println("2.���ӹ������");
			System.out.println("3.ɾ�����");
			System.out.println("4.ɾ���������");
			System.out.println("5.������ֵ");
			System.out.println("6.������Ϣ��ɢ��");
			System.out.println("7.�����罻��ϵ");
			System.out.println("8.ɾ���罻��ϵ");
			System.out.println("9.�������û����߼�����");
			System.out.println("10.���ӻ�����Ȧ");
			System.out.println("11.�鿴��־");
			System.out.println("����end�˳�");

			int choice;

			while (true) {
				System.out.println("������:");
				String s = in.nextLine();
				if (s.matches("[1-9]|10|11")) {
					choice = Integer.valueOf(s);
					break;
				} else if (s.equals("end")) {
					System.out.println("ллʹ��");
					System.exit(0);
				} else {
					System.out.println("�Ƿ�����(������1,2,3,4,end)");
				}
			}

			switch (choice) {
			case 1: {
				double radius = 0;

				while (true) {
					System.out.println("�������°뾶:");
					String s = in.nextLine();
					System.out.println(s);

					Pattern number = Pattern.compile("[0-9]+.*\\d*e*[0-9]*");
					Matcher m = number.matcher(s);
					if (m.find()) {
						radius = Double.valueOf(m.group());
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����(������number���ͻ�end)");
					}
				}
				Circle.addTrack(new Track(radius));
				log.info("*������¹�����뾶Ϊ"+radius);
				break;
			}

			case 2: {

				while (true) {
					System.out.println("���������û���Ϣ:");
					Pattern Person = Pattern.compile("([A-Za-z0-9]+),([0-9]+),(M|F)");
					String s = in.nextLine();
					Matcher m = Person.matcher(s);
					if (m.find()) {
						Person p = new Person(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
						Circle.addTrackObject(p, Circle.getTracks().get(Circle.getTracks().size() - 1));
						log.info("*�������µ��û�"+p.getName());
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				break;
			}

			case 3: {
				int index;

				while (true) {
					System.out.println("������Ҫɾ���Ĺ����:");
					String s = in.nextLine();
					if (s.matches("[0-9]+")) {
						index = Integer.valueOf(s) - 1;
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				Circle.getTracks().remove(index);
				log.info("*ɾ���˹��"+index);
				adjust();
				break;
			}

			case 4: {

				while (true) {
					System.out.println("������Ҫɾ��������:");
					String s = in.nextLine();
					if (s.matches("[A-Za-z0-9]+")) {
						Circle.removeTrackObject(s);
						log.info("*ɾ��������"+s);
						adjust();
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				break;
			}

			case 5: {
				CircularOrbitAPIs<Planet> a = new CircularOrbitAPIs<Planet>();
				System.out.println(a.getObjectDistributionEntropy(Circle));
				log.info("*��������="+a.getObjectDistributionEntropy(Circle));
				break;
			}

			case 6: {

				while (true) {
					System.out.println("�������û�����");
					Pattern name = Pattern.compile("[A-Za-z0-9]+");
					String s = in.nextLine();
					Matcher m = name.matcher(s);
					if (m.find()) {
						System.out.println(diffusivity(m.group()));
						log.info("*��������Ϣ��ɢ��="+diffusivity(m.group()));
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				break;
			}

			case 7: {

				while (true) {
					System.out.println("�������û�1,�û�2,���ܶ�");
					Pattern socialtie = Pattern.compile("([A-Za-z0-9]+),([A-Za-z0-9]+),((0.(\\d)+))");
					String s = in.nextLine();
					Matcher m = socialtie.matcher(s);
					if (m.find()) {
						addRelation(m.group(1), m.group(2), Double.valueOf(m.group(3)));
						log.info("*���������罻��ϵ<"+m.group(1)+","+m.group(2)+","+Double.valueOf(m.group(3))+">");
						adjust();
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				break;
			}

			case 8: {

				while (true) {
					System.out.println("�������û�1,�û�2");
					Pattern socialtie = Pattern.compile("([A-Za-z0-9]+),([A-Za-z0-9]+)");
					String s = in.nextLine();
					Matcher m = socialtie.matcher(s);
					if (m.find()) {
						removeRelation(m.group(1), m.group(2));
						log.info("*ɾ�����罻��ϵ<"+m.group(1)+","+m.group(2)+">");
						adjust();
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				break;
			}

			case 9: {
				while (true) {
					System.out.println("�������û�1,�û�2");
					Pattern socialtie = Pattern.compile("([A-Za-z0-9]+),([A-Za-z0-9]+)");
					String s = in.nextLine();
					Matcher m = socialtie.matcher(s);
					if (m.find()) {
						System.out.println(CircularOrbitAPIs.getLogicalDistance(graph, m.group(1), m.group(2)));
						log.info("*�������߼�����<"+m.group(1)+","+m.group(2)+","+CircularOrbitAPIs.getLogicalDistance(graph, m.group(1), m.group(2))+">");
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				break;
			}

			case 10: {
				break;
			}
			
			case 11:{
				System.out.println("1.����");
				System.out.println("2.����");
				System.out.println("����end����");
				
				String line;
				StringBuilder str = new StringBuilder();
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/log/StellarLog.log")))) ;

				ArrayList<String> logs = new ArrayList<String>();
				
				while ((line = br.readLine()) != null) {
					logs.add(line);
				}
				
				int LogChoice = 0;

				while (true) {
					System.out.println("������:");
					String s = in.nextLine();
					if (s.matches("[1-2]")) {
						LogChoice = Integer.valueOf(s);
						break;
					} else if (s.equals("end")) {
						break;
					} else {
						System.out.println("�Ƿ�����(������1,2,3,4,end)");
					}
				}
				
				System.out.println(LogChoice);
				
				if(LogChoice == 0) {
					break;
				}else if(LogChoice==1) {
					for(String s:logs) {
						if(s.contains("*")) {
							System.out.println(s);
						}
					}
					break;
				}else if(LogChoice==2) {
					for(String s:logs) {
						if(!s.contains("*")) {
							System.out.println(s);
						}
					}
					break;
				}
			}
			}

			if (choice == 10) {
				break;
			}
		}

		launch(args);
	}
}
