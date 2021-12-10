import { Component ,ViewChild, ElementRef,AfterViewInit } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http'
import { ApiService } from './api/api.service';
import { Shape } from './model/shape.model';
import { Point } from './model/point.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit{

  constructor(private api: ApiService){}

  title = 'Paint';
  @ViewChild('canvas')
  canvas: ElementRef = {} as ElementRef;
  ctx: CanvasRenderingContext2D = {} as CanvasRenderingContext2D;
  @ViewChild('color')
  color: ElementRef = {} as ElementRef;
  @ViewChild('lineWidth')
  lineWidth: ElementRef = {} as ElementRef;
  bounds: any;
  frame: Array<Shape> = [];
  isFrameLoaded: boolean = false;
  isDrawing: boolean = false;
  isSelecting: boolean = false;
  isMoving: boolean = false;
  x1!: number;
  x2!: number;
  y1!: number;
  y2!: number;
  action!: string;
  buttons: boolean[] = [];
  fill: boolean = false;
  dummyPoint: Point = new Point(0, 0);
  shape!: Shape;
  ngAfterViewInit(): void {
    this.ctx = this.canvas.nativeElement.getContext('2d');
    this.ctx.canvas.width = window.innerWidth * 0.97;
    this.ctx.canvas.height = window.innerHeight * 0.75;
    this.bounds = this.canvas.nativeElement.getBoundingClientRect();
    this.canvas.nativeElement.addEventListener('mousedown', this.start.bind(this));
    this.canvas.nativeElement.addEventListener('mouseup', this.end.bind(this));
    this.canvas.nativeElement.addEventListener('mousemove', this.drag.bind(this));

    this.canvas.nativeElement.addEventListener('mousedown', this.select.bind(this));

    this.canvas.nativeElement.addEventListener('mousedown', this.moveStart.bind(this));
    this.canvas.nativeElement.addEventListener('mousemove', this.moveDrag.bind(this));
    
  }

  drawShape(sh: Shape){
    switch(sh.shapeType.toLowerCase()){
      case "rectangle":
        this.drawRectangle(sh.p1.x, sh.p1.y, sh.p2.x, sh.p2.y);
        break;
      case "square":
        this.drawSquare(sh.p1.x, sh.p1.y, sh.p2.x, sh.p2.y);
        break;
      case "line":
        this.drawLine(sh.p1.x, sh.p1.y, sh.p2.x, sh.p2.y);
        break;
      case "triangle":
        this.drawTriangle(sh.p1.x, sh.p1.y, sh.p2.x, sh.p2.y, sh.p3.x, sh.p3.y)
        break;
      case "ellipse":
        this.drawEllipse(sh.p1.x, sh.p1.y, sh.r1, sh.r2);
        break;
      case "circle":
        this.drawCircle(sh.p1.x, sh.p1.y, sh.r1);
        break;
      case "pen":
        this.pen(this.x2, this.y2);
    }
  }

  getFrame(){
    this.api.get("/draw").subscribe(
      (shapes: Array<Shape>) =>{
        this.frame = shapes;
      },
      error => {
        
      },
      () => {
        this.drawFrame();
      });
  }

  drawFrame(){
    this.ctx.clearRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    this.frame.forEach(
      (sh: Shape) => {
        this.drawShape(sh);
      }
    )
  }

  toggleSelect(){
    this.isSelecting = !this.isSelecting;
  }

  select(event : MouseEvent){
    if(this.isSelecting){
      let x = event.clientX - this.bounds.left;
      let y = event.clientY - this.bounds.top;
      this.api.post("/select", {x, y}).subscribe(
        (shapes: Array<Shape>) =>
      shapes.forEach((sh) => {
        this.shape = sh;
        switch(sh.shapeType.toLowerCase()){
          case "rectangle":
          case "square":
          case "line":
            this.shape.p1.x = sh.p1.x + 5;
            this.shape.p1.y = sh.p1.y + 5;
            this.shape.p2.x = sh.p2.x - 5;
            this.shape.p2.y = sh.p2.y - 5;
            break;
          case "triangle":
            this.shape.p1.x = sh.p1.x;
            this.shape.p1.y = sh.p1.y + 5;
            this.shape.p2.x = sh.p2.x - 5;
            this.shape.p2.y = sh.p2.y - 5;
            this.shape.p3.x = sh.p3.x + 5;
            this.shape.p3.y = sh.p3.y - 5;
            break;
          case "ellipse":
            this.shape.r1 = sh.r1 + 5;
            this.shape.r2 = sh.r2 + 5;
            break;
          case "circle":
            this.shape.r1 = sh.r1 + 5;
            break;
          case "pen":
            this.pen(this.x2, this.y2);
        }
        this.drawShape(this.shape);
      }
      ));
    }
  }

  fillSwitch(){
    this.fill = !this.fill;
  }

  toggleMove(){
    this.isMoving = !this.isMoving;
  }

  clicked(value: string){
    this.isSelecting = false;
    this.api.send("/deselect", 0);
    for (let i :number = 0; i < 11; i++) {
      this.buttons[i] = false;
    }
    enum buttons{"line", "circle", "ellipse", "triangle", "rectangle", "square", "pen", "resize", "move", "copy", "delete"};
    this.buttons[buttons[value as keyof typeof buttons]] = true;
    this.action = value;
  }

  start(event : MouseEvent){
    if(this.isSelecting || this.isMoving)
      return;
    this.getFrame();
    this.isDrawing = true;
    this.x1 = event.clientX - this.bounds.left;
    this.y1 = event.clientY - this.bounds.top;
    this.ctx.beginPath();
  }

  drag(event : MouseEvent){
    if(this.isSelecting || this.isMoving)
      return;
    if(this.isDrawing){
      this.drawFrame();
      this.x2 = event.clientX - this.bounds.left;
      this.y2 = event.clientY - this.bounds.top;
      this.shape = new Shape(this.action, this.lineWidth.nativeElement.value, this.color.nativeElement.value, this.fill, new Point(0 ,0), new Point(0 ,0), new Point(0 ,0), 0, 0);
      switch(this.action){
        case "line":
          this.shape.p1.x = this.x1;
          this.shape.p1.y = this.y1;
          this.shape.p2.x = this.x2;
          this.shape.p2.y = this.y2;
          break;
        case "square":
          let x = this.x1;
          let y = this.y1;
          let w = this.x2 - this.x1;
          let h = this.y2 - this.y1;
          if(w != h){
            if(Math.abs(w) > Math.abs(h)){
              w = h * Math.sign(h) * Math.sign(w);
            }
            else{
              h = w * Math.sign(w) * Math.sign(h);
            }
          }
          this.shape.p1.x = x;
          this.shape.p1.y = y;
          this.shape.p2.x = w;
          this.shape.p2.y = h;
          break;
        case "rectangle":
          this.shape.p1.x = this.x1;
          this.shape.p1.y = this.y1;
          this.shape.p2.x = this.x2 - this.x1;
          this.shape.p2.y = this.y2 - this.y1;
          break;
        case "triangle":
          this.shape.p1.x = this.x1;
          this.shape.p1.y = this.y1;
          this.shape.p2.x = this.x2;
          this.shape.p2.y = this.y2;
          this.shape.p3.x = this.x1 - (this.x2 - this.x1);
          this.shape.p3.y = this.y2;
          break;
        case "ellipse":
          this.shape.p1.x = this.midpoint(this.x1, this.x2);
          this.shape.p1.y = this.midpoint(this.y1, this.y2);
          this.shape.r1 = Math.floor(Math.abs(this.x2 - this.x1) / 2.0);
          this.shape.r2 = Math.floor(Math.abs(this.y2 - this.y1) / 2.0);
          break;
        case "circle":
          this.shape.p1.x = this.x1;
          this.shape.p1.y = this.y1;
          this.shape.r1 = this.distance(this.x1, this.y1, this.x2, this.y2);
          break;
        case "pen":
          this.pen(this.x2, this.y2);
      }
      this.drawShape(this.shape);
    }
  }

  end(event : MouseEvent){
    if(this.isSelecting || this.isMoving)
      return;
    this.isDrawing = false;
    this.api.send("/create", this.shape).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
      }
    );
  }

  drawLine(x1: number, y1: number, x2: number, y2: number){
    this.ctx.beginPath();
    this.ctx.moveTo(x1, y1);
    this.ctx.lineTo(x2, y2);
    this.draw();
  }

  drawCircle(x: number, y: number, radius: number){
    this.ctx.beginPath();
    this.ctx.arc(x, y, radius, 0, Math.PI * 2);
    this.draw();
  }

  drawEllipse(x: number, y: number, radiusX: number, radiusY: number){
    this.ctx.beginPath();
    this.ctx.ellipse(x, y, radiusX, radiusY, 0, 0, Math.PI * 2);
    this.draw();
  }

  drawTriangle(x1: number, y1: number, x2: number, y2: number, x3: number, y3: number){
    this.ctx.beginPath();
    this.ctx.moveTo(x1, y1);
    this.ctx.lineTo(x2, y2);
    this.ctx.lineTo(x3, y3);
    this.ctx.closePath();
    this.draw();
  }

  drawRectangle(x: number, y: number, w: number, h: number){
    this.ctx.lineWidth = this.lineWidth.nativeElement.value;
    if(this.fill){
      this.ctx.fillStyle = this.color.nativeElement.value;
      this.ctx.fillRect(x, y, w, h);
    }
    else{
      this.ctx.strokeStyle = this.color.nativeElement.value;
      this.ctx.strokeRect(x, y, w, h);
    }
  }

  drawSquare(x: number, y: number, w: number, h: number){
    if(w != h){
      if(Math.abs(w) > Math.abs(h)){
        w = h * Math.sign(h) * Math.sign(w);
      }
      else{
        h = w * Math.sign(w) * Math.sign(h);
      }
    }
    this.drawRectangle(x, y, w, h);
  }

  draw(){
    this.ctx.lineWidth = this.lineWidth.nativeElement.value;
    if(this.fill && this.action != "line"){
      this.ctx.fillStyle = this.color.nativeElement.value;
      this.ctx.fill();
    }
    else{
      this.ctx.strokeStyle = this.color.nativeElement.value;
      this.ctx.stroke();
    }
  }

  pen(x: number, y: number){
    this.ctx.lineCap = "round";
    this.ctx.lineWidth = this.lineWidth.nativeElement.value;
    this.ctx.lineTo(x, y);
    this.ctx.stroke();
    this.ctx.lineCap = "butt";
  } 

  distance(x1 : number, y1 : number, x2 : number, y2 : number) : number{
    return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2))
 }

  midpoint(a : number , b : number): number{
    return Math.floor((a + b) / 2.0);
  }

  undo(){
    this.api.send("/undo", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      }
    )
  }

  redo(){
    this.api.send("/redo", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      }
    )
  }

  save(){
    this.api.send("/save", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      }
    )
  }

  load(){
    this.api.send("/load", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      });
  }
  
  clear(){
    this.api.send("/clear", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      });
  }

  resize(){
    this.api.send("/resize", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      });
  }

  move(){
    this.api.send("/move", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      });
  }

  copy(){
    this.api.send("/copy", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      });
  }

  delete(){
    this.api.send("/delete", 0).subscribe(
      () => {},
      () => {},
      () =>{
        this.getFrame();
        this.drawFrame();
      });
  }

  moveStart(event: MouseEvent){
    if(!this.isMoving)
      return;
    let x = event.clientX - this.bounds.left;
    let y = event.clientY - this.bounds.top;
    this.api.send("/setInitialPosition", {x, y}).subscribe();
  }

  moveDrag(event: MouseEvent){
    if(!this.isMoving)
      return;
    let x = event.clientX - this.bounds.left;
    let y = event.clientY - this.bounds.top;
    this.api.send("/doAction", {x, y}).subscribe();
  }
}
