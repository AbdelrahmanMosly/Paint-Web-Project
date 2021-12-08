package com.example.Paint;

import ShapeWithDimensionsFactory.ShapeWithDimensions;
import ShapeWithDimensionsFactory.ShapeWithDimensionsFactory;
import ShapeWithCartesianFactory.ShapeWithCartesian;
import ShapeWithCartesianFactory.ShapeWithCartesianFactory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaintApplicationTests {

	@Test
	void contextLoads() {
		ShapeWithDimensionsFactory shapeWithDimensionsFactory=new ShapeWithDimensionsFactory();
		ShapeWithDimensions shape1;
		shape1 = ShapeWithDimensionsFactory.createShape("Circle",
				0,
				0,
				new int[] {10},
				"Red",
				true);
		System.out.println(shape1.toString());
		shape1.setPosition(5,3);
		shape1.setFill(false);
		System.out.println(shape1);

		ShapeWithDimensions shape2;
		shape2 = ShapeWithDimensionsFactory.createShape("Ellipse",
				0,
				0,
				new int[] {5,1},
				"Blue",
				true);
		System.out.println(shape2.toString());
		shape2.setPosition(2,5);
		System.out.println(shape2);
		shape1.setDimensions(new int[] {1});
		System.out.println(shape1);
		ShapeWithCartesianFactory shapeWithCartesianFactory=new ShapeWithCartesianFactory();

		ShapeWithCartesian shape3;
		shape3 = ShapeWithCartesianFactory.createShape("Line",
				new int[][] {{1,2},{3,4}},
				"Green",
				true);
		System.out.println(shape3.toString());

		ShapeWithCartesian shape4;
		shape4 = ShapeWithCartesianFactory.createShape("Triangle",
				new int[][] {{1,2},{3,4},{5,6}},
				"pink",
				false);
		System.out.println(shape4.toString());

		ShapeWithCartesian shape5;
		shape5 = ShapeWithCartesianFactory.createShape("square",
				new int[][] {{1,2},{3,4}},
				"purble",
				false);
		System.out.println(shape5.toString());

		shape5.setColor("bte5e");
		System.out.println(shape5.toString());


	}


}
