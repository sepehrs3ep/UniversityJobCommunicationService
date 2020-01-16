import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IJob } from '@app/core/model/job/job.model';
@Injectable({
  providedIn: 'root'
})
export class JobService {
  constructor(private http: HttpClient) { }
  add(job: IJob) {
    return this.http.post('api/jobs', job);
  }
}
