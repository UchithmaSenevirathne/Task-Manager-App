import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TaskService, Task, Status } from '../../../../../../auth/services/task/task.service';
import { UserService } from '../../../../../../auth/services/user/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-task-form',
  standalone:true,
  providers: [TaskService, UserService],
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './task-form.component.html',
  styleUrl: './task-form.component.scss'
})
export class TaskFormComponent implements OnInit{
  @Input() task: Task | null = null;
  @Output() close = new EventEmitter<boolean>();

  formData: Task = {
    title: '',
    description: '',
    status: Status.TO_DO,
    createdAt: '',
    userId: 0
  };

  statusList = Object.values(Status);

  constructor(private taskService: TaskService, private userService: UserService) {}

  ngOnInit() {
    if (this.task) {
      this.formData = { ...this.task };
    }
  }

  save() {
    const username = localStorage.getItem('username');
    if (!username) return;

    this.userService.getUserIdByUsername(username).subscribe(userId => {
      this.formData.userId = userId;
      this.formData.createdAt = new Date().toISOString();

      if (this.task?.taskId) {
        this.taskService.updateTask(this.task.taskId, this.formData).subscribe(() => this.close.emit(true));
      } else {
        this.taskService.createTask(this.formData).subscribe(() => this.close.emit(true));
      }
    });
  }
}
