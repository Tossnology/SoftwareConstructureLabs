package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import centralObject.Nucleus;
import circularOrbit.CircularOrbit;
import javafx.application.Application;
import javafx.stage.Stage;
import phisicalObject.Electron;
import phisicalObject.Planet;
import track.Track;

public class AtomStructure extends Application{
	
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		
		ReadFile(new File("src/txts/AtomicStructure.txt"));
		
		index(args);
	}

	private final static CircularOrbit<Nucleus, Electron> Structure = CircularOrbit.getEmpty();

	/**
	 * ����ԾǨ
	 * 
	 * @param source
	 *            ��ʼ���
	 * @param target
	 *            Ŀ����
	 */
	public static void Transition(Track source, Track target) {
		Structure.changeTrack("electron", source, target);
	}

	/**
	 * ���ļ���ȡ����
	 * 
	 * @param filename
	 *            �ļ�·��
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void ReadFile(File filename) {
		String line;
		StringBuilder str = new StringBuilder();
		Pattern ElementName = Pattern.compile("ElementName ::= ([A-Z][a-z])");
		Pattern NumberOfTracks = Pattern.compile("NumberOfTracks ::= ([0-9]+)");
		Pattern NumberOfElectron = Pattern.compile("NumberOfElectron ::= (([0-9]+/[0-9]+;)+)([0-9]+/[0-9]+)");

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

		Matcher m1 = ElementName.matcher(str);
		Matcher m2 = NumberOfTracks.matcher(str);
		Matcher m3 = NumberOfElectron.matcher(str);

		while (m1.find()) {
			Structure.addCentralObject(new Nucleus(m1.group(1)));
		}

		while (m2.find()) {
			for (int i = 0; i < Integer.valueOf(m2.group(1)); i++) {
				Structure.addTrack(new Track(i + 1));
			}
		}

		while (m3.find()) {
			String s = m3.group(1) + m3.group(3);
			String[] list = s.split(";");
			for (String t : list) {
				String[] f = t.split("/");
				int radius = Integer.valueOf(f[0]);
				int number = Integer.valueOf(f[1]);
				for (int i = 0; i < number; i++) {
					Structure.addTrackObject(new Electron(), new Track(radius));
				}
			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		CircularOrbitHelper.visualizeAtomStructure(Structure, primaryStage);
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
			System.out.println("6.����ԾǨ");
			System.out.println("7.���ӻ�ԭ�ӽṹ");
			System.out.println("����end�˳�");

			int choice;

			while (true) {
				System.out.println("������:");
				String s = in.nextLine();
				if (s.matches("[1-7]")) {
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
				Structure.addTrack(new Track(radius));
				break;
			}

			case 2: {

				while (true) {
					System.out.println("�������µ������ڹ����:");
					Pattern Track = Pattern.compile(
							"[0-9]+");
					String s = in.nextLine();
					Matcher m = Track.matcher(s);
					if (m.find()) {
						Electron e = new Electron();
						Structure.addTrackObject(e, Structure.getTracks().get(Integer.valueOf(m.group())-1));
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
						index = Integer.valueOf(s)-1;
						break;
					} else if (s.equals("end")) {
						System.out.println("ллʹ��");
						System.exit(0);
					} else {
						System.out.println("�Ƿ�����");
					}
				}
				Structure.getTracks().remove(index);
				break;
			}

			case 4: {

				while (true) {
					System.out.println("������Ҫɾ��������:");
					String s = in.nextLine();
					if (s.matches("[A-Za-z0-9]+")) {
						Structure.removeTrackObject(s);
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
				System.out.println(a.getObjectDistributionEntropy(Structure));
				break;
			}

			case 6: {

				while (true) {
					System.out.println("������Դ���,Ŀ����");
					Pattern Track = Pattern.compile(
							"([0-9]+),([0-9]+)");
					String s = in.nextLine();
					Matcher m = Track.matcher(s);
					if (m.find()) {
						Transition(Structure.getTracks().get(Integer.valueOf(m.group(1))-1),Structure.getTracks().get(Integer.valueOf(m.group(2))-1));
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
				break;
			}
			}
			
			if(choice==7) {
				break;
			}
		}
		
		launch(args);
	}
}
