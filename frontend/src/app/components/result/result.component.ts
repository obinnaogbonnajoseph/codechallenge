import { Component, OnInit } from '@angular/core';
import { CodeTask, User } from 'src/app/model/models';
import { ComponentService } from 'src/app/service/component.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  winners: User[] = [];

  total = 0;

  constructor(private service: ComponentService) { }

  ngOnInit(): void {
    this.service.getResult(1).subscribe(({ users, total }) => {
      this.winners = users;
      this.total = total;
      console.table(this.winners)
    })
  }

  getTaskNames(tasks: CodeTask[]): string {
    return tasks.map(task => task.name).join(", ")
  }

}
