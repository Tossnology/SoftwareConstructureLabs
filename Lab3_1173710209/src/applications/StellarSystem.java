package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;

import javafx.application.Application;
import javafx.stage.Stage;
import phisicalObject.Planet;
import track.Track;

public class StellarSystem extends Application {
	private static Scanner in = new Scanner(System.in);
	
	private static double T = 0;

	public static void main(String[] args) {
		ReadFile(new File("src/txts/StellarSystem.txt"));
		
		index(args);
	}

	private final static CircularOrbit<Stellar, Planet> system = CircularOrbit.getEmpty();

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
	 */
	public static void ReadFile(File filename) {
		String line;
		StringBuilder str = new StringBuilder();
		Pattern Stellar = Pattern.compile("Stellar ::= <([A-Za-z0-9]+),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*)>");
		Pattern Planet = Pattern.compile(
				"Planet ::= <([A-Za-z0-9]+),(Solid|Gas|Liquid),([A-Za-z0-9]+),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*),([0-9]+.*\\d*e*[0-9]*),(CW|CCW),([0-9]+.*\\d*e*[0-9]*)>");

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

		Matcher m1 = Stellar.matcher(str);
		Matcher m2 = Planet.matcher(str);

		while (m1.find()) {
			system.addCentralObject(new Stellar(m1.group(1), Double.valueOf(m1.group(2)), Double.valueOf(m1.group(3))));
		}

		while (m2.find()) {
			Track track = new Track(Double.valueOf(m2.group(5)));
			Planet planet = new Planet(m2.group(1), m2.group(2), m2.group(3), Double.valueOf(m2.group(4)),
					Double.valueOf(m2.group(5)), Double.valueOf(m2.group(6)), m2.group(7),
					Double.valueOf(m2.group(8)) % 360);

			system.addTrack(track);
			system.addTrackObject(planet, track);

		}

		system.sortTracks();
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
	 */
	public static void index(String[] args) {
		while (true) {
			System.out.println("1.�����¹��");
			System.out.println("2.���ӹ������");
			System.out.println("3.ɾ�����");
			System.out.println("4.ɾ���������");
			System.out.println("5.������ֵ");
			System.out.println("6.����tʱ�̸�����λ��");
			System.out.println("7.����tʱ���������Ǽ��");
			System.out.println("8.���ӻ�tʱ�����Ƿֲ�");
			System.out.println("����end�˳�");

			int choice;

			while (true) {
				System.out.println("������:");
				String s = in.nextLine();
				if (s.matches("[1-8]")) {
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
				break;
			}

			case 4: {

				while (true) {
					System.out.println("������Ҫɾ��������:");
					String s = in.nextLine();
					if (s.matches("[A-Za-z0-9]+")) {
						system.removeTrackObject(s);
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
			}
			
			if(choice==8) {
				break;
			}
		}
		
		launch(args);
	}
}
