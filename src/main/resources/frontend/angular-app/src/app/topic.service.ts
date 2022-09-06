import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Topic } from './topic';
import { Post } from './post';
import { environment } from 'src/environments/environment'

@Injectable({
  providedIn: 'root'
})
export class TopicService {
private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTopics(): Observable<Topic[]>{ // Get the data from the API
   return this.http.get<Topic[]>('https://wexnermedical.osu.edu/blog/topics?blogId=F88D7CA3-2E58-4A80-AEFE-A0755FCD491D')
   }

   public loadBlogs(topic: String): Observable<Post[]> { // Get blog posts that belong to a given topic
    return this.http.get<Post[]>(`${this.apiServerUrl}/blog/topic/${topic}`)

   }

   public getAllBlogs(): Observable<Post[]> { // Get all blog posts
     return this.http.get<Post[]>(`${this.apiServerUrl}/blog/allBlogs`)
     }
}
