package edu.iastate.cs228.hw4;

/**
 *  
 * @author
 *
 */

import java.util.Comparator;

/**
 * 
 * This class compares two points p1 and p2 by polar angle with respect to a reference point.  
 * It is known that the reference point is not above either p1 or p2, and in the case that
 * either or both of p1 and p2 have the same y-coordinate, not to their right. 
 *
 */
public class PointComparator implements Comparator<Point>
{
	private Point referencePoint; 

	/**
	 * 
	 * @param p reference point
	 */
	PointComparator(Point p)
	{
		referencePoint = p; 
	}

	/**
	 * Use cross product and dot product to implement this method.  Do not take square roots 
	 * or use trigonometric functions. See the PowerPoint notes on how to carry out cross and 
	 * dot products. 
	 * 
	 * Call comparePolarAngle() and compareDistance(). 
	 * 
	 * @param p1
	 * @param p2
	 * @return -1 if one of the following three conditions holds: 
	 *                a) p1 and referencePoint are the same point but p2 is a different point; 
	 *                b) neither p1 nor p2 equals referencePoint, and the polar angle of 
	 *                   p1 with respect to referencePoint is less than that of p2; 
	 *                c) neither p1 nor p2 equals referencePoint, p1 and p2 have the same polar 
	 *                   angle w.r.t. referencePoint, and p1 is closer to referencePoint than p2. 
	 *         0  if p1 and p2 are the same point  
	 *         1  if one of the following three conditions holds:
	 *                a) p2 and referencePoint are the same point but p1 is a different point; 
	 *                b) neither p1 nor p2 equals referencePoint, and the polar angle of
	 *                   p1 with respect to referencePoint is greater than that of p2;
	 *                c) neither p1 nor p2 equals referencePoint, p1 and p2 have the same polar
	 *                   angle w.r.t. referencePoint, and p1 is further to referencePoint than p2. 
	 *                   
	 */
	public int compare(Point p1, Point p2)
	{
		// TODO - combine the condition statements after testing
		
		// 1.a 
		if (p1.equals(referencePoint) && !p1.equals(p2)){ 					
			return -1;
		}
		// 1.b
		else if (!p1.equals(referencePoint) && !p2.equals(referencePoint) && comparePolarAngle(p1, p2)<0){
			return -1;
		}
		// 1.c
		else if (!p1.equals(referencePoint) && !p2.equals(referencePoint) && comparePolarAngle(p1, p2)==0 && compareDistance(p1, p2)<0){
			return -1;
		}
		
		// 2.a
		else if (p2.equals(referencePoint) && !p1.equals(p2)){
			return 1;
		}
		//2.b
		else if(!p1.equals(referencePoint)&&!p2.equals(referencePoint) && comparePolarAngle(p1, p2)>0){
			return 1;
		}
		//2.c
		else if (!p1.equals(referencePoint) && !p2.equals(referencePoint) && comparePolarAngle(p1, p2)==0 && compareDistance(p1, p2)>0){
			return 1;
		}
			
		return 0;	//same point 
	}


	/**
	 * Compare the polar angles of two points p1 and p2 with respect to referencePoint.  Use 
	 * cross products.  Do not use trigonometric functions. 
	 * 
	 * Precondition:  p1 and p2 are distinct points. 
	 * 
	 * @param p1
	 * @param p2
	 * @return   -1  if p1 equals referencePoint or its polar angle with respect to referencePoint
	 *               is less than that of p2. 
	 *            0  if p1 and p2 have the same polar angle. 
	 *            1  if p2 equals referencePoint or its polar angle with respect to referencePoint
	 *               is less than that of p1. 
	 */
	public int comparePolarAngle(Point p1, Point p2) 
	{
		// if p1 equals referencePoint or its polar angle with respect to referencePoint is less than that of p2
		if (p1.equals(referencePoint) || polarAngleHelper(p1, p2)>0){
			return -1; 
		}
		// p2 equals referencePoint or its polar angle with respect to referencePoint
		// is less than that of p1.
		else if(p2.equals(referencePoint) || polarAngleHelper(p1, p2)<0){
			return 1;
		}
		else{
			return 0; //points aren't the reference and polar angles are the same
		}
	}

	/**
	 * Polar Angle Helper Method:
	 * returns:
	 * 
	 * p1<p2	>0
	 * p1>p2	<0
	 * p1==p2	0
	 */
	private double polarAngleHelper(Point p1, Point p2){
		//re-center points around reference point
		Point newP1= new Point(p1.getX()+referencePoint.getX(), p1.getY()+referencePoint.getY());
		Point newP2= new Point(p2.getX()+referencePoint.getX(), p2.getY()+referencePoint.getY());

		//cross product logic
		return (newP1.getX() * newP2.getY()) - (newP1.getY() * newP2.getX());
	}

	/**
	 * Compare the distances of two points p1 and p2 to referencePoint.  Use dot products. 
	 * Do not take square roots. 
	 * 
	 * @param p1
	 * @param p2
	 * @return   -1   if p1 is closer to referencePoint 
	 *            0   if p1 and p2 are equidistant to referencePoint
	 *            1   if p2 is closer to referencePoint
	 */
	public int compareDistance(Point p1, Point p2)
	{
		// TODO - use dot products
		if(distanceHelper(p1, p2)<0){		//if p1 is closer to referencePoint 
			return -1;
		}
		else if (distanceHelper(p1, p2)>0){ //if p2 is closer to referencePoint
			return 1;
		}
		else{
			return 0; 						//equivalent distance
		}
	}
	
	/**
	 * Polar Angle Helper Method:
	 * returns:
	 * 
	 * d1<d2	<0
	 * d1>d2	>0
	 * d1==d2	0
	 */
	private double distanceHelper(Point p1, Point p2){
		//find distance from referencePoint for each point
		double d1 = Math.sqrt(((p1.getX() - referencePoint.getX())*(p1.getX() - referencePoint.getX())) + ((p1.getY() - referencePoint.getY())*(p1.getY() - referencePoint.getY())));
		double d2 = Math.sqrt(((p2.getX() - referencePoint.getX())*(p2.getX() - referencePoint.getX())) + ((p2.getY() - referencePoint.getY())*(p2.getY() - referencePoint.getY())));
		//return difference
		return d1-d2;

	}
}
