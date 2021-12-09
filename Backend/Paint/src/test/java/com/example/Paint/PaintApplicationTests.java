package com.example.Paint;

import InputHandling.PointInPolygon;
import ShapeWithDimensionsFactory.ShapeWithDimensions;
import ShapeWithDimensionsFactory.ShapeWithDimensionsFactory;
import ShapeWithCartesianFactory.ShapeWithCartesian;
import ShapeWithCartesianFactory.ShapeWithCartesianFactory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

@SpringBootTest
class PaintApplicationTests {

	@Test
	void  contextLoads() {
		ShapeWithDimensionsFactory shapeWithDimensionsFactory=new ShapeWithDimensionsFactory();

		PointInPolygon pointInPolygon=new PointInPolygon();

		ShapeWithDimensions shape1;
		shape1 = ShapeWithDimensionsFactory.createShape("Circle",
				new Point(0, 0),
				new int[] {10},
				"Red",
				true,
				2);
		System.out.println(shape1.toString());
		System.out.println(pointInPolygon.pointInCircle(new Point(3,5),shape1));

		System.out.println(pointInPolygon.pointInCircle(new Point(11,5),shape1));

		ShapeWithDimensions shape2;
		shape2 = ShapeWithDimensionsFactory.createShape("Ellipse",
				new Point(0, 0),
				new int[] {5,1},
				"Blue",
				true,
				2);
		System.out.println(shape2.toString());

		System.out.println(pointInPolygon.pointInEllipse(new Point(3,0),shape2));

		System.out.println(pointInPolygon.pointInEllipse(new Point(11,5),shape2));

		ShapeWithCartesianFactory shapeWithCartesianFactory=new ShapeWithCartesianFactory();

		ShapeWithCartesian shape3;
		Point shape3Points[]={new Point(1,1),new Point(3,3)};
		shape3 = ShapeWithCartesianFactory.createShape("Line",
				shape3Points,
				"Green",
				true,
				2);
		System.out.println(shape3.toString());
		System.out.println(pointInPolygon.pointOnLine(new Point(2,2),shape3));

		System.out.println(pointInPolygon.pointOnLine(new Point(4,4),shape3));


		ShapeWithCartesian shape4;
		shape4 = ShapeWithCartesianFactory.createShape("Triangle",
				new Point[] {new Point(1,2),new Point(3,7),new Point(5,6)},
				"pink",
				false,
				2);
		System.out.println(pointInPolygon.pointInTriangle(new Point(3,5),shape4));
		System.out.println(shape4.toString());

		ShapeWithCartesian shape5;
		shape5 = ShapeWithCartesianFactory.createShape("square",
				new Point[] {new Point(1,2),new Point(3,4)},
				"purble",
				false,
				2);
		System.out.println(shape5.toString());
		System.out.println(pointInPolygon.pointInSquare(new Point(2,2),shape5));

		System.out.println(pointInPolygon.pointInSquare(new Point(4,4),shape5));


		shape5.setColor("bte5e");
		System.out.println(shape5.toString());


	}


}
