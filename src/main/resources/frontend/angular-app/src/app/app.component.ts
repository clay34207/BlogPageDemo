import { Component, OnInit } from '@angular/core';
import { Topic } from './topic';
import { Post } from './post';
import { TopicService } from './topic.service';
import { HttpErrorResponse } from '@angular/common/http';

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
  blogsPerPage: number = 6; // The maximum number of blog posts that can be shown per page
  constructor(private topicService:TopicService){}


async ngOnInit() {
   this.getAllBlogs(); // Load all blogs by default
   this.getTopics(); // Load blog topics for the dropdown
 }

public getAllBlogs() { // Returns all of the blog posts from the CSV file, regardless of topic
  this.topicService.getAllBlogs().subscribe(
  (response: Post[])=> {
    this.posts = response;
    this.showMessageIfEmpty(response.length); // Show a message if there are no blog posts
    this.page = 1;

  },
  (error:HttpErrorResponse) => {
    alert(error.message);
    }
  );
 }

public getTopics(): void { // Return the result of the API call in an array and remove duplicates

   this.topicService.getTopics().subscribe(
    (response:Topic[])=> {
    response = [...new Map(response.map(topic => [topic.Name, topic])).values()] //Borrowed this line from StackOverflow. Removes duplicates.
    this.topics = response;
  },
  (error:HttpErrorResponse) => {
  alert(error.message);
  }
  );
}

public loadBlogs(topic: String): void {
  this.topicService.loadBlogs(topic).subscribe(
      (response:Post[])=> {
      this.posts = response;
      this.showMessageIfEmpty(response.length); // display a message if there are no blogs for the selected topic
      this.page = 1;
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
   onTableDataChange(event: any) { // Used by pagination
      this.page = event;
    }
}
