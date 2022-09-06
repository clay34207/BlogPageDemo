import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ChangeDetectorRef} from '@angular/core';
import { Topic } from './topic'
import { Post } from './post'
import { TopicService } from './topic.service'
import { HttpErrorResponse } from '@angular/common/http';
import {MatSelectModule} from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public topics: Topic[];
  public posts: Post[];
  public selectedTopic: String;
  public htmlVar: String;
  public isVisible: boolean;
  public page: number = 1;
  public currentVal: String = "All";
  count: number = 0;
    tableSize: number = 6;
    tableSizes: any = [3, 6, 9, 12];
  constructor(private topicService:TopicService, private route: Router, private ref:ChangeDetectorRef){}


async ngOnInit() {
   this.getAllBlogs();
   this.getTopics();
     this.topics = this.topics.filter((x, i, a) => a.indexOf(x) == i)
    alert(this.topics)

 }


 public showMessage(len: number){
 alert(len)

 }

public getAllBlogs() {
  this.topicService.getAllBlogs().subscribe(
  (response: Post[])=> {
    this.posts = response;
    this.currentVal = "All";
     this.showMessageIfEmpty(response.length);

  },
  (error:HttpErrorResponse) => {
    alert(error.message);
    }
  );
 }

 public getPost(topic: String) {
  this.topicService.getPost(topic).subscribe(
  (response: Post[])=> {
    this.posts = response;
  },
  (error:HttpErrorResponse) => {
    alert(error.message);
    }

  );
 }

public async getTopics(): Promise<void> {

   this.topicService.getTopics().subscribe(
    (response:Topic[])=> {
    response = Array.from(response.reduce((m, t) => m.set(t.Name, t), new Map()).values());
    this.topics = response;

  },
  (error:HttpErrorResponse) => {
  alert(error.message);
  }
  );


}


public loadBlogs(topic: String): void {
  this.topicService.getPost(topic).subscribe(
      (response:Post[])=> {
       this.posts = response;
      this.showMessageIfEmpty(response.length);
      this.currentVal = topic;
    },
    (error:HttpErrorResponse) => {
    alert(error.message);
    }
    );
}

public showMessageIfEmpty(len: number){
   if(len==0)
      this.isVisible = true;
    else
      this.isVisible = false;
  }

   onTableDataChange(event: any) {
      this.page = event;
      //this.getAllBlogs();
    }
    onTableSizeChange(event: any): void {
      this.tableSize = event.target.value;
      this.page = 1;
     // this.getAllBlogs();
    }

}
