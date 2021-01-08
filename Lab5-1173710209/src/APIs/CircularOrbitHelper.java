package APIs;

import java.util.ArrayList;

import applications.StellarSystem;

import centralObject.Nucleus;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;
import graph.Graph;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import phisicalObject.Electron;
import phisicalObject.Person;
import phisicalObject.Planet;
import track.Track;


public class CircularOrbitHelper {
  /**
   * 可视化恒星轨道系统.
   * @param system 轨道系统
   * @param primaryStage 可视化 
   * @param time 时间
   */
  public static void visualizeStellarSystem(CircularOrbit<Stellar, Planet> system, Stage primaryStage, double time) {

    Pane p = new Pane();
    Scene scene = new Scene(p, 390, 390);

    Circle stellar = new Circle();
    stellar.setId(system.getCentralObjects().get(0).getName());
    stellar.setCenterX(195.0f);
    stellar.setCenterY(195.0f);
    stellar.setRadius(10.0f);
    stellar.setStroke(Color.RED);
    stellar.setFill(Color.RED);
    p.getChildren().add(stellar);

    Text name = new Text();
    name.setText(stellar.getId());
    name.setX(stellar.getCenterX());
    name.setY(stellar.getCenterY());

    p.getChildren().add(name);

    for (Planet planet : system.getTrackObjects().keySet()) {
      Circle track = new Circle();

      track.setCenterX(195.0f);
      track.setCenterY(195.0f);

      Track tmp = system.getTrackObjects().get(planet);

      String s = String.valueOf(tmp.getRadius());

      track.setId(s);
      track.setRadius((system.getTracks().indexOf(tmp) + 1) * 20.0f);
      track.setStroke(Color.BLACK);
      track.setFill(null);
      p.getChildren().add(track);

      Circle star = new Circle();
      star.setId(planet.getName());
      star.setRadius(5.0f);
      star.setStroke(Color.BLUE);
      star.setFill(Color.BLUE);
      star.setCenterX(track.getCenterX()
          + track.getRadius() * Math.cos(StellarSystem.CalculatePosition(planet, time) * Math.PI / 180));
      star.setCenterY(track.getCenterY()
          - track.getRadius() * Math.sin(StellarSystem.CalculatePosition(planet, time) * Math.PI / 180));

      p.getChildren().add(star);

      Text name1 = new Text();
      name1.setText(star.getId());
      name1.setX(star.getCenterX());
      name1.setY(star.getCenterY());

      p.getChildren().add(name1);
    }

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * 可视化原子结构.
   * @param Structure 原子结构
   * @param primaryStage 可视化
   */
  public static void visualizeAtomStructure(CircularOrbit<Nucleus, Electron> Structure, Stage primaryStage) {

    Pane p = new Pane();
    Scene scene = new Scene(p, 390, 390);

    Circle stellar = new Circle();
    stellar.setId(Structure.getCentralObjects().get(0).getName());
    stellar.setCenterX(195.0f);
    stellar.setCenterY(195.0f);
    stellar.setRadius(10.0f);
    stellar.setStroke(Color.RED);
    stellar.setFill(Color.RED);
    p.getChildren().add(stellar);

    Text name = new Text();
    name.setText(stellar.getId());
    name.setX(stellar.getCenterX());
    name.setY(stellar.getCenterY());

    p.getChildren().add(name);

    for (Track t : Structure.getTracks()) {
      Circle track = new Circle();

      track.setCenterX(195.0f);
      track.setCenterY(195.0f);

      track.setId(String.valueOf(t.getRadius()));
      track.setRadius(t.getRadius() * 20.0f);
      track.setStroke(Color.BLACK);
      track.setFill(null);
      p.getChildren().add(track);
    }

    int i = 0;
    for (Electron e : Structure.getTrackObjects().keySet()) {

      Circle star = new Circle();
      star.setId("electron");
      star.setRadius(5.0f);
      star.setStroke(Color.BLUE);
      star.setFill(Color.BLUE);

      star.setCenterX(195 + Structure.getTrackObjects().get(e).getRadius() * 20.0f * Math.cos(7 * i * Math.PI / 180));
      star.setCenterY(195 - Structure.getTrackObjects().get(e).getRadius() * 20.0f * Math.sin(7 * i * Math.PI / 180));

      p.getChildren().add(star);

      i++;
    }

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * 可视化社交网络.
   * @param Circle 朋友圈
   * @param graph 关系图
   * @param primaryStage 可视化
   */
  public static void visualizeSocialNetworkCircle(CircularOrbit<Person, Person> Circle, Graph<String> graph,
      Stage primaryStage) {

    Pane p = new Pane();
    Group group = new Group();
    ArrayList<Circle> people = new ArrayList<Circle>();
    Scene scene = new Scene(p, 390, 390);

    Circle stellar = new Circle();
    stellar.setId(Circle.getCentralObjects().get(0).getName());
    stellar.setCenterX(195.0f);
    stellar.setCenterY(195.0f);
    stellar.setRadius(10.0f);
    stellar.setStroke(Color.RED);
    stellar.setFill(Color.RED);
    group.getChildren().add(stellar);
    people.add(stellar);

    Text name = new Text();
    name.setText(stellar.getId());
    name.setX(stellar.getCenterX());
    name.setY(stellar.getCenterY());

    p.getChildren().add(name);

    int i = 0;

    for (Person person : Circle.getTrackObjects().keySet()) {
      if (Circle.getTrackObjects().get(person).getRadius() > 0) {
        Circle track = new Circle();

        track.setCenterX(195.0f);
        track.setCenterY(195.0f);

        Track tmp = Circle.getTrackObjects().get(person);

        String s = String.valueOf(tmp.getRadius());

        track.setId(s);
        track.setRadius((Circle.getTracks().indexOf(tmp) + 1) * 30.0f);
        track.setStroke(Color.BLACK);
        track.setFill(null);
        p.getChildren().add(track);

        Circle star = new Circle();
        star.setId(person.getName());
        star.setRadius(5.0f);
        star.setStroke(Color.BLUE);
        star.setFill(Color.BLUE);
        star.setCenterX(track.getCenterX() + track.getRadius() * Math.cos(50 * i * Math.PI / 180));
        star.setCenterY(track.getCenterY() - track.getRadius() * Math.sin(50 * i * Math.PI / 180));

        group.getChildren().add(star);
        people.add(star);

        Text name1 = new Text();
        name1.setText(star.getId());
        name1.setX(star.getCenterX());
        name1.setY(star.getCenterY());

        p.getChildren().add(name1);

        i++;
      }
    }

    for (int j = 0; j < people.size(); j++) {
      for (int k = j + 1; k < people.size(); k++) {
        if (graph.targets(people.get(j).getId()).containsKey(people.get(k).getId())) {
          Line l = new Line();
          l.setStartX(people.get(j).getCenterX());
          l.setStartY(people.get(j).getCenterY());
          l.setEndX(people.get(k).getCenterX());
          l.setEndY(people.get(k).getCenterY());

          p.getChildren().add(l);
        }
      }
    }

    p.getChildren().add(group);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
