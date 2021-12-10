import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shape } from '../model/shape.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  send(path: string, body: Object): Observable<Shape> {
    return this.http.post<Shape>(`${environment.api_url}${path}`, JSON.stringify(body), httpOptions)
  }

  post(path: String, body: Object): Observable<Array<Shape>> {
    return this.http.post<Array<Shape>>(`${environment.api_url}${path}`, JSON.stringify(body), httpOptions);
  }

  get(path: String): Observable<Array<Shape>> {
    return this.http.get<Array<Shape>>(`${environment.api_url}${path}`, httpOptions);
  }
}
