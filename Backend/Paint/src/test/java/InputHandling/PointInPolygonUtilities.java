package InputHandling;

import java.awt.*;

public class PointInPolygonUtilities {
    public  int triangleVectorProduct(Point p1,Point p2,Point p3){
        return p1.x*(p2.y-p3.y)-p2.x*(p1.y-p3.y)+p3.x*(p1.y-p2.y);
    }
    public double distance(Point p1,Point p2){
        return Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2));
    }

    /**
     *
     * @param p1
     * @param p2
     * @param p3
     * @return if p1 is between p2 and p3
     */
    public boolean betweenTwoPoints(Point p1,Point p2,Point p3){
        return p1.x>=Math.min(p2.x,p3.x) && p1.x<=Math.max(p2.x,p3.x)
            && p1.y>=Math.min(p2.y,p3.y) && p1.y<=Math.max(p2.y,p3.y);
    }
    public boolean onSameSlope(Point p1,Point p2,Point p3){
        return ((p1.y-p2.y)/(p1.x-p2.x)) == ((p3.y-p2.y)/(p3.x-p2.x));
    }
    public boolean ellipseChecker(Point p1,Point center,int rx,int ry){
        return (Math.pow(p1.x-center.x,2)/Math.pow(rx,2))+(Math.pow(p1.y-center.y,2)/Math.pow(ry,2))<=1;
    }
}
