import { TitleCasePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CodeTask, SubmitTask } from 'src/app/model/models';
import { ComponentService } from 'src/app/service/component.service';

@Component({
  selector: 'app-solve',
  templateUrl: './solve.component.html',
  styleUrls: ['./solve.component.css']
})
export class SolveComponent implements OnInit {

  tasks: CodeTask[] = [];

  submitText = 'Submit';

  error = false;

  form = this.fb.group({
    username: [this.username, Validators.required],
    description: [''],
    task: ['Select Task', Validators.required],
    code: ['', Validators.required]
  })

  constructor(private fb: FormBuilder,
    private componentService: ComponentService, private router: Router) {
    this.form.get('task')?.valueChanges.subscribe((val: CodeTask) => this.updateForm(val))
  }

  ngOnInit(): void {
    this.getTasks();
  }

  updateForm(val: CodeTask): void {
    this.form.patchValue({
      description: val?.description ?? '',
      code: val?.code ?? ''
    })
    if (this.error) {
      this.submitText = "Submit";
      this.error = false;
    }
  }

  getTasks(): void {
    this.componentService.getTasks().subscribe(tasks => this.tasks = tasks)
  }

  getTaskName(task: CodeTask): string {
    return `${task.name} (${new TitleCasePipe().transform(task.type)})`
  }

  submit() {
    this.submitText = '...Submitting'
    localStorage.setItem('username', this.form.get('username')?.value)
    const data: SubmitTask = {
      userName: this.form.get('username')?.value ?? '',
      taskName: this.task?.name ?? '',
      code: this.form.get('code')?.value ?? '',
      type: this.task?.type
    }
    this.componentService.submit(data).subscribe({
      complete: () => this.router.navigate(['/result']),
      error: _ => {
        this.submitText = "Could not submit."
        this.error = true;
      }
    })
  }

  get description() {
    return this.form.get('description')?.value
  }

  get username() {
    return localStorage.getItem('username') ?? '';
  }

  get task(): CodeTask {
    return this.form.get('task')?.value
  }

}
