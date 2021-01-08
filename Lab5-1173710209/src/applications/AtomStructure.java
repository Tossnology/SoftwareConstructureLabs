package applications;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import centralObject.Nucleus;
import circularOrbit.CircularOrbit;
import exceptions.WrongFileTypeException;
import exceptions.WrongTrackNumberException;

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
import javafx.application.Application;
import javafx.stage.Stage;
import phisicalObject.Electron;
import phisicalObject.Planet;
import track.Track;

public class AtomStructure extends Application {

  private static Scanner in = new Scanner(System.in);

  static Logger log = Logger.getLogger("AtomStructure");
  static FileHandler fh = null;

  public static void main(String[] args) throws WrongTrackNumberException, IOException, WrongFileTypeException {
    log.setUseParentHandlers(false);
    fh = new FileHandler("src/log/AtomLog.log", true);
    fh.setFormatter(new MyFormatter());
    log.addHandler(fh);

    while (true) {
      try {
        System.out.println("请输入文件路径(src/txts/...):");
        String fn = in.nextLine();
        log.info("*输入文件路径:" + fn);
        ReadFile(new File(fn));
        break;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        continue;
      } catch (WrongFileTypeException e) {
        log.info(e.getMessage());
        e.printStackTrace();
        continue;
      } catch (WrongTrackNumberException e) {
        log.info(e.getMessage());
        e.printStackTrace();
        continue;
      }
    }
    index(args);
  }

  private static CircularOrbit<Nucleus, Electron> Structure = CircularOrbit.getEmpty();

  /**
   * 获取轨道系统.
   * 
   * @return 轨道系统
   */
  public static CircularOrbit<Nucleus, Electron> getStructure() {
    return Structure;
  }

  /**
   * 电子跃迁.
   * 
   * @param source
   *          起始轨道
   * @param target
   *          目标轨道
   */
  public static void transition(Track source, Track target) {
    Structure.changeTrack("electron", source, target);
  }

  /**
   * 从文件读取数据.
   * 
   * @param filename
   *          文件路径
   * @throws WrongTrackNumberException 轨道数错误异常
   * @throws IOException IO异常
   * @throws WrongFileTypeException 文件类型错误异常
   */
  public static void ReadFile(File filename) throws WrongTrackNumberException, IOException, WrongFileTypeException {
    Structure = CircularOrbit.getEmpty();

    int trackNum = 0;
    boolean flag1 = false;
    boolean flag2 = false;
    boolean flag3 = false;

    String line;
    StringBuilder str = new StringBuilder();
    Pattern ElementName = Pattern.compile("ElementName ::= ([A-Z][a-z])");
    Pattern NumberOfTracks = Pattern.compile("NumberOfTracks ::= ([0-9]+)");
    Pattern NumberOfElectron = Pattern.compile("NumberOfElectron ::= (([0-9]+/[0-9]+;)+)([0-9]+/[0-9]+)");

    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

    while ((line = in.readLine()) != null) {
      str.append(line + "\n");
    }

    Matcher m1 = ElementName.matcher(str);
    Matcher m2 = NumberOfTracks.matcher(str);
    Matcher m3 = NumberOfElectron.matcher(str);

    while (m1.find()) {
      flag1 = true;
      Structure.addCentralObject(new Nucleus(m1.group(1)));
    }

    while (m2.find()) {
      flag2 = true;
      trackNum = Integer.valueOf(m2.group(1));
      for (int i = 0; i < Integer.valueOf(m2.group(1)); i++) {
        Structure.addTrack(new Track(i + 1));
      }
    }

    while (m3.find()) {
      flag3 = true;
      String s = m3.group(1) + m3.group(3);
      String[] list = s.split(";");

      if (list.length != trackNum) {
        WrongTrackNumberException e = new WrongTrackNumberException("轨道数目不对");
        throw e;
      }

      for (String t : list) {
        String[] f = t.split("/");
        int radius = Integer.valueOf(f[0]);
        int number = Integer.valueOf(f[1]);
        for (int i = 0; i < number; i++) {
          Structure.addTrackObject(new Electron(), new Track(radius));
        }
      }
    }

    if ((flag1 && flag2 && flag3) == false) {
      WrongFileTypeException e = new WrongFileTypeException("文件类型不对");
      throw e;
    }
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    CircularOrbitHelper.visualizeAtomStructure(Structure, primaryStage);
  }

  /**
   * 主界面.
   * 
   * @param args
   *          main的参数
   * @throws IOException IO异常
   */
  public static void index(String[] args) throws IOException {
    while (true) {
      System.out.println("1.增加新轨道");
      System.out.println("2.增加轨道物体");
      System.out.println("3.删除轨道");
      System.out.println("4.删除轨道物体");
      System.out.println("5.计算熵值");
      System.out.println("6.电子跃迁");
      System.out.println("7.可视化原子结构");
      System.out.println("8.查看日志");
      System.out.println("输入end退出");

      int choice;

      while (true) {
        System.out.println("请输入:");
        String s = in.nextLine();
        if (s.matches("[1-8]")) {
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
          Structure.addTrack(new Track(radius));
          log.info("*添加了新轨道，半径为" + radius);
          break;
        }

        case 2: {
  
          while (true) {
            System.out.println("请输入新电子所在轨道号:");
            Pattern Track = Pattern.compile("[0-9]+");
            String s = in.nextLine();
            Matcher m = Track.matcher(s);
            if (m.find()) {
              Electron e = new Electron();
              Structure.addTrackObject(e, Structure.getTracks().get(Integer.valueOf(m.group()) - 1));
              log.info("*在轨道" + Structure.getTracks().get(Integer.valueOf(m.group()) - 1).getRadius() + "增加了新的电子");
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
              index = Integer.valueOf(s) - 1;
              break;
            } else if (s.equals("end")) {
              System.out.println("谢谢使用");
              System.exit(0);
            } else {
              System.out.println("非法输入");
            }
          }
          Structure.getTracks().remove(index);
          log.info("*删除了轨道" + index);
          break;
        }

        case 4: {
  
          while (true) {
            System.out.println("请输入要删除的物体:");
            String s = in.nextLine();
            if (s.matches("[A-Za-z0-9]+")) {
              Structure.removeTrackObject(s);
              log.info("*删除了物体" + s);
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
          System.out.println(a.getObjectDistributionEntropy(Structure));
          log.info("*计算了熵=" + a.getObjectDistributionEntropy(Structure));
          break;
        }

        case 6: {
  
          while (true) {
            System.out.println("请输入源轨道,目标轨道");
            Pattern Track = Pattern.compile("([0-9]+),([0-9]+)");
            String s = in.nextLine();
            Matcher m = Track.matcher(s);
            if (m.find()) {
              transition(Structure.getTracks().get(Integer.valueOf(m.group(1)) - 1),
                  Structure.getTracks().get(Integer.valueOf(m.group(2)) - 1));
              log.info("*将电子从轨道" + Structure.getTracks().get(Integer.valueOf(m.group(1)) - 1).getRadius() + "跃迁到轨道"
                  + Structure.getTracks().get(Integer.valueOf(m.group(2)) - 1));
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
          break;
        }
  
        case 8: {
          System.out.println("1.操作");
          System.out.println("2.错误");
          System.out.println("输入end返回");
  
          String line;
          StringBuilder str = new StringBuilder();
          BufferedReader br = new BufferedReader(
              new InputStreamReader(new FileInputStream(new File("src/log/StellarLog.log"))));
  
          ArrayList<String> logs = new ArrayList<String>();
  
          while ((line = br.readLine()) != null) {
            logs.add(line);
          }
  
          int LogChoice = 0;
  
          while (true) {
            System.out.println("请输入:");
            String s = in.nextLine();
            if (s.matches("[1-2]")) {
              LogChoice = Integer.valueOf(s);
              break;
            } else if (s.equals("end")) {
              break;
            } else {
              System.out.println("非法输入(请输入1,2,3,4,end)");
            }
          }
  
          System.out.println(LogChoice);
  
          if (LogChoice == 0) {
            break;
          } else if (LogChoice == 1) {
            for (String s : logs) {
              if (s.contains("*")) {
                System.out.println(s);
              }
            }
            break;
          } else if (LogChoice == 2) {
            for (String s : logs) {
              if (!s.contains("*")) {
                System.out.println(s);
              }
            }
            break;
          } else {
            break;
          }
        }
  
        default: {
          break;
        }
      }

      if (choice == 7) {
        break;
      }
    }

    launch(args);
  }
}
