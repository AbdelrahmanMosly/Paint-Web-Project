import { Point } from "./point.model";

export class Shape{
    constructor(public shapeType: string, public strokeSize: number, public color: string, public fill: boolean,
        public p1: Point, public p2: Point, public p3: Point, public r1: number, public r2: number){
        this.shapeType = shapeType;
        this.strokeSize = strokeSize;
        this.color = color;
        this.fill = fill;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.r1 = r1;
        this.r2 = r2;
    }
}