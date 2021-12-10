import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'text/plain',
  })
};

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  send(path: string, body: Object): Observable<Object> {
    return this.http.post<Object>(`${environment.api_url}${path}`, JSON.stringify(body), httpOptions)
  }

  get(path: String, value: string): Observable<Object> {
    return this.http.get<Object>(`${environment.api_url}${path}/${value}`, httpOptions);
  }
}
