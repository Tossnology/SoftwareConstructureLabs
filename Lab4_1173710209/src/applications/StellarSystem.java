package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;
import exceptions.DirectionException;
import exceptions.NeedRereadFileException;
import exceptions.SamePlanetException;
import exceptions.StateException;
import exceptions.StellarNumberException;
import exceptions.WrongFileTypeException;
import exceptions.WrongTrackNumberException;
import javafx.application.Application;
import javafx.stage.Stage;
import phisicalObject.Planet;
import track.Track;

public class StellarSystem extends Application {
	private static Scanner in = new Scanner(System.in);
	
	private static double T = 0;
	
	static Logger log = Logger.getLogger("StellarSystem");
	static FileHandler fh=null;

	public static void main(String[] args) throws IOException{
		
		log.setUseParentHandlers(false);
		fh = new FileHandler("src/log/StellarLog.log",true);
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

	private static CircularOrbit<Stellar, Planet> system = CircularOrbit.getEmpty();

	/**
	 * ����tʱ������planet�ĽǶ�λ��
	 * 
	 * @param planet
	 *            ����
	 * @param t
	 *            ʱ��
	 * @return tʱ�̽Ƕ�λ��
	 */
	public static double CalculatePosition(Planet planet, double t) {
		double result = 0;
		if (planet.getDirection().equals("CCW")) {
			result = (planet.getInitAngle() + t * planet.getSpeed() / planet.getOrbitRadius()) % 360;
		}
		if (planet.getDirection().equals("CW")) {
			result = (planet.getInitAngle() - t * planet.getSpeed() / planet.getOrbitRadius() + t * 360) % 360;
		}
		return result;
	}

	/**
	 * ����tʱ�������Ǽ��������
	 * 
	 * @param planet1
	 *            ����1
	 * @param planet2
	 *            ����2
	 * @param t
	 *            ʱ��
	 * @return tʱ���������������
	 */
	public static double CalculateDistance(Planet planet1, Planet planet2, double t) {
		double result = 0;
		double r1 = planet1.getOrbitRadius();
		double r2 = planet2.getOrbitRadius();
		double sitha1 = CalculatePosition(planet1, t);
		double sitha2 = CalculatePosition(planet2, t);
		result = Math.sqrt(r1 * r1 + r2 * r2 - 2 * r1 * r2 * Math.cos(sitha1 - sitha2));
		return result;
	}

	/**
	 * ���ļ���ȡ����
	 * 
	 * @param filename
	 *            �ļ�·��
	 * @throws IOException 
	 * @throws WrongFileTypeException 
	 * @throws NeedRereadFileException 
	 */
	public static void ReadFile(File filename) throws IOException, NeedRereadFileException {
		system = CircularOrbit.getEmpty();
		
		boolean flag1 = false;
		boolean flag2 = false;
		boolean reRead = false;
		
		String line;
		StringBuilder str = new StringBuilder();
		Pattern Stellar = Pattern.compile("Stellar ::= <([A-Za-z0-9]+),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*)>");
		Pattern Planet = Pattern.compile(
				"Planet ::= <([A-Za-z0-9]+),([A-Za-z0-9]+),([A-Za-z0-9]+),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*),([A-Za-z0-9]+),([0-9]+.*\\d*e*[0-9]*)>");

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename))) ;

		while ((line = in.readLine()) != null) {
			str.append(line + "\n");
		}

		Matcher m1 = Stellar.matcher(str);
		Matcher m2 = Planet.matcher(str);

		while (m1.find()) {
			flag1 = true;
			try {
				if(system.getCentralObjects().size()==1) {
					throw new StellarNumberException("����ֻ����һ��");
				}
				
				system.addCentralObject(new Stellar(m1.group(1), Double.valueOf(m1.group(2)), Double.valueOf(m1.group(3))));
			}catch(StellarNumberException e) {
				log.info(e.getMessage());
				e.printStackTrace();
				reRead = true;
			}
			
		}

		ArrayList<String> planets = new ArrayList<String>();
		
		while (m2.find()) {
			flag2 = true;
			
			Track track = new Track(Double.valueOf(m2.group(5)));
			
			try {
				if(planets.contains(m2.group(1))) {
					throw new SamePlanetException("��������ͬ������"+m2.group(1));
				}
				
				if(!m2.group(2).equals("Solid")&&!m2.group(2).equals("Gas")&&!m2.group(2).equals("Liquid")) {
					throw new StateException("״̬����ΪSolid/Gas/Liquid"+"����Ϊ"+m2.group(2));
				}
				
				if(!m2.group(7).equals("CW")&&!m2.group(7).equals("CCW")) {
					throw new DirectionException("�˶��������ΪCW/CCW"+"����Ϊ"+m2.group(7));
				}
				
				
				Planet planet = new Planet(m2.group(1), m2.group(2), m2.group(3), Double.valueOf(m2.group(4)),
						Double.valueOf(m2.group(5)), Double.valueOf(m2.group(6)), m2.group(7),
						Double.valueOf(m2.group(8)) % 360);
				
				planets.add(m2.group(1));
			

			system.addTrack(track);
			system.addTrackObject(planet, track);
			
			}catch(StateException e) {
				log.info(e.getMessage());
				e.printStackTrace();
				reRead = true;
			}catch(DirectionException e) {
				log.info(e.getMessage());
				e.printStackTrace();
				reRead = true;
			}catch(SamePlanetException e) {
				log.info(e.getMessage());
				e.printStackTrace();
				reRead = true;
			}
		}

		try {
		if((flag1&&flag2)==false) {
			WrongFileTypeException e = new WrongFileTypeException("�ļ����Ͳ���");
			throw e; 
		}
		}catch(WrongFileTypeException e) {
			e.printStackTrace();
			reRead = true;
		}
		
		if(reRead == true) {
			throw new NeedRereadFileException("�ļ������⣬��Ҫ���¶�ȡ�ļ�");
		}
		system.sortTracks();
		
		log.info("*�ѳɹ���ȡ�ļ�"+filename.getPath());
	}

	public ArrayList<Track> getTracks() {
		return system.getTracks();
	}

	public void sortTracks() {
		system.sortTracks();
	}

	public CircularOrbit<Stellar, Planet> getSystem() {
		return system;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		CircularOrbitHelper.visualizeStellarSystem(system, primaryStage, T);
	}
	
	/**
	 * ������
	 * @param args main�Ĳ���
	 * @throws IOException 
	 */
	public static void index(String[] args) throws IOException {
		while (true) {
			System.out.println("1.�����¹��");
			System.out.println("2.���ӹ������");
			System.out.println("3.ɾ�����");
			System.out.println("4.ɾ���������");
			System.out.println("5.������ֵ");
			System.out.println("6.����tʱ�̸�����λ��");
			System.out.println("7.����tʱ���������Ǽ��");
			System.out.println("8.���ӻ�tʱ�����Ƿֲ�");
			System.out.println("9.�鿴��־");
			System.out.println("����end�˳�");

			int choice;

			while (true) {
				System.out.println("������:");
				String s = in.nextLine();
				if (s.matches("[1-9]")) {
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
				system.addTrack(new Track(radius));
				log.info("*������¹�����뾶Ϊ"+radius);
				break;
			}

			case 2: {

				while (true) {
					System.out.println("��������������Ϣ:");
					Pattern Planet = Pattern.compile(
							"([A-Za-z0-9]+),(Solid|Gas|Liquid),([A-Za-z0-9]+),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*),(CW|CCW),([0-9]+.*\\d*e*[0-9]*)");
					String s = in.nextLine();
					Matcher m = Planet.matcher(s);
					if (m.find()) {
						Planet planet = new Planet(m.group(1), m.group(2), m.group(3), Double.valueOf(m.group(4)),
								Double.valueOf(m.group(5)), Double.valueOf(m.group(6)), m.group(7),
								Double.valueOf(m.group(8)) % 360);
						system.addTrackObject(planet, new Track(planet.getOrbitRadius()));
						log.info("*�������µ�����"+planet.getName());
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
						index = Integer.valueOf(s);
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				system.getTracks().remove(index);
				log.info("*ɾ���˹��"+index);
				break;
			}

			case 4: {

				while (true) {
					System.out.println("������Ҫɾ��������:");
					String s = in.nextLine();
					if (s.matches("[A-Za-z0-9]+")) {
						system.removeTrackObject(s);
						log.info("*ɾ��������"+s);
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
				System.out.println(a.getObjectDistributionEntropy(system));
				log.info("*��������="+a.getObjectDistributionEntropy(system));
				break;
			}

			case 6: {

				while (true) {
					System.out.println("������ʱ��t(s):");
					String s = in.nextLine();
					if (s.matches("[0-9]+.*\\d*e*[0-9]*")) {
						double t = Double.valueOf(s);
						for (Planet p : system.getTrackObjects().keySet()) {
							System.out.println(p.getName() + " " + "��ʼ:" + p.getInitAngle() + " " + t + "���:"
									+ CalculatePosition(p, t));
						}
						log.info("*������"+t+"�������ǵ�λ��");
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
					System.out.println("����������1,����2,ʱ��:");
					String s = in.nextLine();
					Pattern distance = Pattern.compile("([A-Za-z0-9]+),([A-Za-z0-9]+),([0-9]+.*\\d*e*[0-9]*)");
					Matcher m = distance.matcher(s);
					if (m.find()) {
						double t = Double.valueOf(m.group(3));

						Planet p1 = null;
						Planet p2 = null;
						for (Planet p : system.getTrackObjects().keySet()) {

							if (p.getName().equals(m.group(1))) {
								p1 = p;
							}

							if (p.getName().equals(m.group(2))) {
								p2 = p;
							}

							if (p1 != null && p2 != null) {
								break;
							}
						}

						System.out.println(String.valueOf(CalculateDistance(p1, p2, t)));
						log.info("*������"+t+"���"+p1.getName()+"��"+p2.getName()+"��ľ���="+CalculateDistance(p1, p2, t));

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
					System.out.println("������ʱ��t(s):");
					String s = in.nextLine();
					if (s.matches("[0-9]+.*\\d*e*[0-9]*")) {
						T=Double.valueOf(s);
						log.info("*����"+T+"���Ŀ��ӻ�����");
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
			
			
			case 9:{
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
			if(choice==8) {
				break;
			}
		}
		
		launch(args);
	}
}
