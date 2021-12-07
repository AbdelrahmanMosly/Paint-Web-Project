package com.example.Paint;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

@SpringBootTest
class PaintApplicationTests {

	@Test
	void contextLoads() {
		ShapeFactory shapeFactory=new ShapeFactory();
		Shape shape1=shapeFactory.createShape("Circle",0,0,new double[] {10.1});
		System.out.println(shape1.toString());
		shape1.setPosition(5,3);
		System.out.println(shape1.toString());
		Shape shape2=ShapeFactory.createShape("Rectangle",0,0,new double[] {5,1});
		System.out.println(shape2.toString());
		shape2.setPosition(2,5);
		System.out.println(shape2.toString());
		shape1.setDimensions(new double[] {1});
		System.out.println(shape1.toString());

	}


}
