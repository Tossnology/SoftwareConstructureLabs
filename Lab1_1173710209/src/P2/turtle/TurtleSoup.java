/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class TurtleSoup {

	/**
	 * Draw a square.
	 * 
	 * @param turtle
	 *            the turtle context
	 * @param sideLength
	 *            length of each side
	 */
	public static void drawSquare(Turtle turtle, int sideLength) {
		// throw new RuntimeException("implement me!");
		turtle.forward(50);
		turtle.turn(90);
		turtle.forward(50);
		turtle.turn(90);
		turtle.forward(50);
		turtle.turn(90);
		turtle.forward(50);
	}

	/**
	 * Determine inside angles of a regular polygon.
	 * 
	 * There is a simple formula for calculating the inside angles of a polygon; you
	 * should derive it and use it here.
	 * 
	 * @param sides
	 *            number of sides, where sides must be > 2
	 * @return angle in degrees, where 0 <= angle < 360
	 */
	public static double calculateRegularPolygonAngle(int sides) {
		// throw new RuntimeException("implement me!");
		double s = (double) (sides);
		return (180 * s - 360) / s;
	}

	/**
	 * Determine number of sides given the size of interior angles of a regular
	 * polygon.
	 * 
	 * There is a simple formula for this; you should derive it and use it here.
	 * Make sure you *properly round* the answer before you return it (see
	 * java.lang.Math). HINT: it is easier if you think about the exterior angles.
	 * 
	 * @param angle
	 *            size of interior angles in degrees, where 0 < angle < 180
	 * @return the integer number of sides
	 */
	public static int calculatePolygonSidesFromAngle(double angle) {
		// throw new RuntimeException("implement me!");
		int s = (int) Math.ceil(360 / (180 - angle));
		return s;
	}

	/**
	 * Given the number of sides, draw a regular polygon.
	 * 
	 * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
	 * draw.
	 * 
	 * @param turtle
	 *            the turtle context
	 * @param sides
	 *            number of sides of the polygon to draw
	 * @param sideLength
	 *            length of each side
	 */
	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		// throw new RuntimeException("implement me!");
		for (int i = 0; i < sides; i++) {
			turtle.forward(sideLength);
			turtle.turn(180 - calculateRegularPolygonAngle(sides));
		}
	}

	/**
	 * Given the current direction, current location, and a target location,
	 * calculate the Bearing towards the target point.
	 * 
	 * The return value is the angle input to turn() that would point the turtle in
	 * the direction of the target point (targetX,targetY), given that the turtle is
	 * already at the point (currentX,currentY) and is facing at angle
	 * currentBearing. The angle must be expressed in degrees, where 0 <= angle <
	 * 360.
	 *
	 * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
	 * 
	 * @param currentBearing
	 *            current direction as clockwise from north
	 * @param currentX
	 *            current location x-coordinate
	 * @param currentY
	 *            current location y-coordinate
	 * @param targetX
	 *            target point x-coordinate
	 * @param targetY
	 *            target point y-coordinate
	 * @return adjustment to Bearing (right turn amount) to get to target point,
	 *         must be 0 <= angle < 360
	 */
	public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
			int targetY) {
		// throw new RuntimeException("implement me!");
		double a = 180 * Math.atan2(targetX - currentX, targetY - currentY) / Math.PI;
		double b = a - currentBearing;
		if (b < 0) {
			return b + 360;
		} else {
			return b;
		}
	}

	/**
	 * Given a sequence of points, calculate the Bearing adjustments needed to get
	 * from each point to the next.
	 * 
	 * Assumes that the turtle starts at the first point given, facing up (i.e. 0
	 * degrees). For each subsequent point, assumes that the turtle is still facing
	 * in the direction it was facing when it moved to the previous point. You
	 * should use calculateBearingToPoint() to implement this function.
	 * 
	 * @param xCoords
	 *            list of x-coordinates (must be same length as yCoords)
	 * @param yCoords
	 *            list of y-coordinates (must be same length as xCoords)
	 * @return list of Bearing adjustments between points, of size 0 if (# of
	 *         points) == 0, otherwise of size (# of points) - 1
	 */
	public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
		// throw new RuntimeException("implement me!");
		int currentX = xCoords.get(0), currentY = yCoords.get(0);
		double currentBearing = 0;
		List<Double> bearings = new ArrayList<>();
		for (int i = 1; i < xCoords.size(); i++) {
			int targetX = xCoords.get(i);
			int targetY = yCoords.get(i);
			double b = calculateBearingToPoint(currentBearing, currentX, currentY, targetX, targetY);
			bearings.add(b);
			currentX = targetX;
			currentY = targetY;
			currentBearing = b;
		}
		return bearings;
	}

	/**
	 * Given a set of points, compute the convex hull, the smallest convex set that
	 * contains all the points in a set of input points. The gift-wrapping algorithm
	 * is one simple approach to this problem, and there are other algorithms too.
	 * 
	 * @param points
	 *            a set of points with xCoords and yCoords. It might be empty,
	 *            contain only 1 point, two points or more.
	 * @return minimal subset of the input points that form the vertices of the
	 *         perimeter of the convex hull
	 */
	public static Set<Point> convexHull(Set<Point> points) {
		// throw new RuntimeException("implement me!");
		Set<Point> convexHull = new HashSet<Point>();
		if (points.isEmpty()) {
			System.out.println("空集");
			return Collections.emptySet();
		} else {

			Point startPoint = Collections.min(points, new PointComparator());
			// System.out.println(startPoint.x()+","+startPoint.y());
			convexHull.add(startPoint);
			Point currentPoint = startPoint;
			Point targetPoint = null;
			double currentBearing = 0;
			while (targetPoint != startPoint) {
				targetPoint = currentPoint;
				for (Point p : points) {
					if (p == currentPoint) {
						continue;
					} else {
						if (targetPoint == currentPoint || calculateBearingToPoint(currentBearing,
								(int) currentPoint.x(), (int) currentPoint.y(), (int) p.x(),
								(int) p.y()) < calculateBearingToPoint(currentBearing, (int) currentPoint.x(),
										(int) currentPoint.y(), (int) targetPoint.x(), (int) targetPoint.y())) {
							targetPoint = p;
						}
					}
				}
				if (calculateBearingToPoint(currentBearing, (int) currentPoint.x(), (int) currentPoint.y(),
						(int) targetPoint.x(), (int) targetPoint.y()) == 0) {
					convexHull.remove(currentPoint);
				} else {
					currentBearing = currentBearing + calculateBearingToPoint(currentBearing, (int) currentPoint.x(),
							(int) currentPoint.y(), (int) targetPoint.x(), (int) targetPoint.y());
				}
				convexHull.add(targetPoint);
				currentPoint = targetPoint;
			}
			return convexHull;
		}
	}

	/**
	 * Draw your personal, custom art.
	 * 
	 * Many interesting images can be drawn using the simple implementation of a
	 * turtle. For this function, draw something interesting; the complexity can be
	 * as little or as much as you want.
	 * 
	 * @param turtle
	 *            the turtle context
	 */
	public static void drawPersonalArt(Turtle turtle) {
		// throw new RuntimeException("implement me!");
		turtle.color(PenColor.RED);
		turtle.turn(90);
		for (int i = 0; i < 5; i++) {
			turtle.forward(100);
			turtle.turn(144);
		}
	}

	/**
	 * Main method.
	 * 
	 * This is the method that runs when you run "java TurtleSoup".
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String args[]) {
		DrawableTurtle turtle = new DrawableTurtle();
		// drawSquare(turtle, 40);
		// drawRegularPolygon(turtle, 3,40);
		 drawPersonalArt(turtle);
		// draw the window
		turtle.draw();
	}

}
//坐标比较器
class PointComparator implements Comparator<Point> {
	public int compare(Point p1, Point p2) {
		if (p1.x() < p2.x() || p1.y() < p2.y()) {
			return -1;
		} else {
			return 1;
		}
	}
}