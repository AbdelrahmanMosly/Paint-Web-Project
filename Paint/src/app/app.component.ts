import { Component ,ViewChild, ElementRef,AfterViewInit } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit{
  @ViewChild('canvas')
  canvas: ElementRef = {} as ElementRef;  
  ctx: CanvasRenderingContext2D = {} as CanvasRenderingContext2D;

  ngAfterViewInit(): void {
    this.ctx = this.canvas.nativeElement.getContext('2d');
    this.ctx.canvas.width = window.innerWidth * 0.97;
    this.ctx.canvas.height = window.innerHeight * 0.75;
    this.ctx.fillStyle = 'rgb(200, 0, 0)';
    this.ctx.strokeStyle = 'rgb(100, 100, 50)';
    this.ctx.fillRect(60, 60, 50, 50);
    this.ctx.strokeRect(10, 10, 100, 50);
    this.ctx.beginPath();
    this.ctx.moveTo(200, 25);
    this.ctx.lineTo(175, 75);
    this.ctx.lineTo(225, 75);
    this.ctx.fillStyle = 'rgb(0, 0, 70)';
    this.ctx.fill();
    this.ctx.beginPath();
    this.ctx.arc(100, 50, 10, 0, Math.PI * 2);
    this.ctx.fill();
    this.ctx.beginPath();
    this.ctx.ellipse(300, 100, 75, 50, 0, 0, Math.PI * 2);
    this.ctx.stroke();
    this.ctx.beginPath();
    this.ctx.moveTo(300, 400);
    this.ctx.lineTo(600, 300);
    this.ctx.strokeStyle = 'rgb(30, 180, 250)';
    this.ctx.stroke();
  }
}