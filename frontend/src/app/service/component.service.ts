import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CodeTask, Result, SubmitTask } from 'src/app/model/models';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ComponentService {

  baseUrl = environment.apiBaseUrl;

  constructor(private httpClient: HttpClient) { }

  get url() {
    return `${this.baseUrl}task/`
  }

  getTasks(): Observable<CodeTask[]> {
    return this.httpClient.get<CodeTask[]>(this.url)
  }

  getResult(page: number): Observable<Result> {
    return this.httpClient.get<Result>(`${this.url}winners/${page}`)
  }

  submit(data: SubmitTask): Observable<any> {
    return this.httpClient.post<any>(`${this.url}submit`, data)
  }

}
