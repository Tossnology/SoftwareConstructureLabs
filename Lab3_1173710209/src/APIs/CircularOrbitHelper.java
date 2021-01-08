package APIs;

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

import java.util.ArrayList;

import applications.StellarSystem;

public class CircularOrbitHelper {
	public static void visualizeStellarSystem(CircularOrbit<Stellar, Planet> system,Stage primaryStage,double T) {

		Pane p = new Pane();
		Scene scene = new Scene(p, 390, 390);

		Circle Stellar = new Circle();
		Stellar.setId(system.getCentralObjects().get(0).getName());
		Stellar.setCenterX(195.0f);
		Stellar.setCenterY(195.0f);
		Stellar.setRadius(10.0f);
		Stellar.setStroke(Color.RED);
		Stellar.setFill(Color.RED);
		p.getChildren().add(Stellar);

		Text Name = new Text();
		Name.setText(Stellar.getId());
		Name.setX(Stellar.getCenterX());
		Name.setY(Stellar.getCenterY());

		p.getChildren().add(Name);

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
			star.setCenterX(track.getCenterX() + track.getRadius() * Math.cos(StellarSystem.CalculatePosition(planet,T) * Math.PI / 180));
			star.setCenterY(track.getCenterY() - track.getRadius() * Math.sin(StellarSystem.CalculatePosition(planet,T) * Math.PI / 180));

			p.getChildren().add(star);

			Text name = new Text();
			name.setText(star.getId());
			name.setX(star.getCenterX());
			name.setY(star.getCenterY());

			p.getChildren().add(name);
		}

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void visualizeAtomStructure(CircularOrbit<Nucleus, Electron> Structure,Stage primaryStage) {

		Pane p = new Pane();
		Scene scene = new Scene(p,390,390);
		
		
		Circle Stellar = new Circle();
		Stellar.setId(Structure.getCentralObjects().get(0).getName());
		Stellar.setCenterX(195.0f);
		Stellar.setCenterY(195.0f);
		Stellar.setRadius(10.0f);
		Stellar.setStroke(Color.RED);
		Stellar.setFill(Color.RED);
		p.getChildren().add(Stellar);
		
		Text Name = new Text();
        Name.setText(Stellar.getId());
        Name.setX(Stellar.getCenterX());
        Name.setY(Stellar.getCenterY());
        
        p.getChildren().add(Name);
        
        for(Track t: Structure.getTracks()) {
        	Circle track = new Circle();
			
			track.setCenterX(195.0f);
			track.setCenterY(195.0f);
			
			track.setId(String.valueOf(t.getRadius()));
			track.setRadius(t.getRadius()*20.0f);
			track.setStroke(Color.BLACK);
	        track.setFill(null);
	        p.getChildren().add(track);
        }
		
        int i = 0;
		for(Electron e: Structure.getTrackObjects().keySet()) {
	        
	        Circle star = new Circle();
	        star.setId("electron");
	        star.setRadius(5.0f);
	        star.setStroke(Color.BLUE);
	        star.setFill(Color.BLUE);
	        
	        star.setCenterX(195+Structure.getTrackObjects().get(e).getRadius()*20.0f*Math.cos(7*i*Math.PI/180));
	        star.setCenterY(195-Structure.getTrackObjects().get(e).getRadius()*20.0f*Math.sin(7*i*Math.PI/180));
	        
	        p.getChildren().add(star);
	        
	        i++;
		}
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void visualizeSocialNetworkCircle(CircularOrbit<Person, Person> Circle,Graph<String> graph,Stage primaryStage) {

		Pane p = new Pane();
		Group group = new Group();
		ArrayList<Circle> people = new ArrayList<Circle>();
		Scene scene = new Scene(p,390,390);
		
		Circle Stellar = new Circle();
		Stellar.setId(Circle.getCentralObjects().get(0).getName());
		Stellar.setCenterX(195.0f);
		Stellar.setCenterY(195.0f);
		Stellar.setRadius(10.0f);
		Stellar.setStroke(Color.RED);
		Stellar.setFill(Color.RED);
		group.getChildren().add(Stellar);
		people.add(Stellar);
		
		Text Name = new Text();
        Name.setText(Stellar.getId());
        Name.setX(Stellar.getCenterX());
        Name.setY(Stellar.getCenterY());
        
        p.getChildren().add(Name);
		
        int i = 0;
        
		for(Person person: Circle.getTrackObjects().keySet()) {
			Circle track = new Circle();
			
			track.setCenterX(195.0f);
			track.setCenterY(195.0f);
			
			Track tmp = Circle.getTrackObjects().get(person);
			
			String s = String.valueOf(tmp.getRadius());
			
			track.setId(s);
			track.setRadius((Circle.getTracks().indexOf(tmp)+1)*30.0f);
			track.setStroke(Color.BLACK);
	        track.setFill(null);
	        p.getChildren().add(track);
	        
	        Circle star = new Circle();
	        star.setId(person.getName());
	        star.setRadius(5.0f);
	        star.setStroke(Color.BLUE);
	        star.setFill(Color.BLUE);
	        star.setCenterX(track.getCenterX()+track.getRadius()*Math.cos(50*i*Math.PI/180));
	        star.setCenterY(track.getCenterY()-track.getRadius()*Math.sin(50*i*Math.PI/180));
	        
	        group.getChildren().add(star);
	        people.add(star);
	        
	        Text name = new Text();
	        name.setText(star.getId());
	        name.setX(star.getCenterX());
	        name.setY(star.getCenterY());
	        
	        p.getChildren().add(name);
	        
	        i++;
		}
		
		for(int j = 0;j<people.size();j++) {
			for(int k = j+1;k<people.size();k++) {
				if(graph.targets(people.get(j).getId()).containsKey(people.get(k).getId())){
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
