import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CodeTask, SubmitTask } from 'src/app/model/models';
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

  submit(data: SubmitTask): Observable<any> {
    return this.httpClient.post<any>(this.url, data)
  }

}
