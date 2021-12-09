import { Component ,ViewChild, ElementRef,AfterViewInit } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit{
  title = 'Paint';
  @ViewChild('canvas')
  canvas: ElementRef = {} as ElementRef;
  ctx: CanvasRenderingContext2D = {} as CanvasRenderingContext2D;
  @ViewChild('color')
  color: ElementRef = {} as ElementRef;
  @ViewChild('lineWidth')
  lineWidth: ElementRef = {} as ElementRef;
  bounds: any;
  isDrawing: boolean = false;
  x1!: number;
  x2!: number;
  y1!: number;
  y2!: number;
  canvasUndo: any[] = [];
  undoIndex: number = 0;
  updateUndo: boolean = false;
  action!: string;
  buttons: boolean[] = [];
  fill: string = "";
  ngAfterViewInit(): void {
    this.ctx = this.canvas.nativeElement.getContext('2d');
    this.ctx.canvas.width = window.innerWidth * 0.97;
    this.ctx.canvas.height = window.innerHeight * 0.75;
    this.bounds = this.canvas.nativeElement.getBoundingClientRect();
    this.canvas.nativeElement.addEventListener('mousedown', this.start.bind(this));
    this.canvas.nativeElement.addEventListener('mouseup', this.end.bind(this));
    this.canvas.nativeElement.addEventListener('mousemove', this.drag.bind(this));
    this.canvasUndo[0] = this.ctx.getImageData(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
  }

  fillSwitch(){
    this.fill = (this.fill == "") ? "fill" : "";
  }
  clicked(value: string){
    for (let i :number = 0; i < 11; i++) {
      this.buttons[i] = false;
    }
    enum buttons{"line", "circle", "ellipse", "triangle", "rectangle", "square", "pen", "resize", "move", "copy", "delete"};
    this.buttons[buttons[value as keyof typeof buttons]] = true;
    this.action = value;
  }

  start(event : MouseEvent){
    this.isDrawing = true;
    this.x1 = event.clientX - this.bounds.left;
    this.y1 = event.clientY - this.bounds.top;
    this.ctx.beginPath();
  }

  drag(event : MouseEvent){
    if(this.isDrawing){
      if(!this.updateUndo){
        this.updateUndo = true;
      }
      this.ctx.putImageData(this.canvasUndo[this.undoIndex], 0, 0);
      this.x2 = event.clientX - this.bounds.left;
      this.y2 = event.clientY - this.bounds.top;
      switch(this.action){
        case "rectangle":
          this.drawRectange(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
          break;
        case "square":
          this.drawSquare(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
          break;
        case "triangle":
          this.drawTriangle(this.x1, this.y1, this.x2, this.y2, this.x1 - (this.x2 - this.x1), this.y2);
          break;
        case "ellipse":
          this.drawEllipse(this.midpoint(this.x1, this.x2), this.midpoint(this.y1, this.y2), Math.floor(Math.abs(this.x2 - this.x1) / 2.0), Math.floor(Math.abs(this.y2 - this.y1) / 2.0));
          break;
        case "circle":
          this.drawCircle(this.x1, this.y1, this.distance(this.x1, this.y1, this.x2, this.y2));
          break;
        case "line":
          this.drawLine(this.x1, this.y1, this.x2, this.y2)
          break;
        case "pen":
          this.pen(this.x2, this.y2);
      }
    }
  }

  end(event : MouseEvent){
    this.isDrawing = false;
    if(this.updateUndo){
      this.updateUndo = false;
      this.undoIndex++;
      this.canvasUndo[this.undoIndex] = this.ctx.getImageData(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    }
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

  drawRectange(x: number, y: number, w: number, h: number){
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
    this.ctx.save();
    this.ctx.lineCap = "round";
    this.ctx.lineWidth = this.lineWidth.nativeElement.value;
    this.ctx.lineTo(x, y);
    this.ctx.stroke();
    this.ctx.restore();
  }

  distance(x1 : number, y1 : number, x2 : number, y2 : number) : number{
    return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2))
 }

  midpoint(a : number , b : number): number{
    return Math.floor((a + b) / 2.0);
  }

  undo(){
    if(this.undoIndex > 0){
      this.undoIndex--;
      this.ctx.putImageData(this.canvasUndo[this.undoIndex], 0, 0);
    }
  }

  redo(){
    if(this.undoIndex < this.canvasUndo.length - 1){
      this.undoIndex++
      this.ctx.putImageData(this.canvasUndo[this.undoIndex], 0, 0);
    }
  }
}
