import { Component, OnInit } from '@angular/core';
import {
  TaskService,
  Task,
  Status,
} from '../../../../../../auth/services/task/task.service';
import { TaskFormComponent } from '../../task-form/task-form/task-form.component';
import { TaskDetailComponent } from '../../task-detail/task-detail/task-detail.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { StorageService } from '../../../../../../auth/services/storage/storage.service';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-task-list',
  standalone: true,
  providers: [TaskService],
  imports: [TaskFormComponent, TaskDetailComponent, CommonModule, HttpClientModule, MatIconModule],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.scss',
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  filteredTasks: Task[] = [];
  currentDate = new Date();
  selectedTask: Task | null = null;
  filter: Status | null = null;

  showFormModal = false;
  showDetailModal = false;
  showFilter = false;

  Status = Status; // Add this to your component class


  constructor(private taskService: TaskService, private router: Router) {}

  ngOnInit() {
    if (!StorageService.isLoggedIn()) {
      this.router.navigate(['/']);
    } else {
      this.loadTasks();
    }
  }

  loadTasks() {
    this.taskService.getAllTasks().subscribe((data) => {
      this.tasks = data;
      this.filteredTasks = data;
    });
  }

  filterBy(status: Status) {
    this.filter = status;
    this.filteredTasks = this.tasks.filter((t) => t.status === status);
  }

  toggleFilter() {
    this.showFilter = !this.showFilter;
  }

  clearFilter() {
    this.filter = null;
    this.filteredTasks = [...this.tasks];
  }

  openCreate() {
    this.selectedTask = null;
    this.showFormModal = true;
  }

  openEdit(task: Task) {
    this.selectedTask = task;
  this.showFormModal = true;
  }

  openDetails(task: Task) {
    this.selectedTask = task;
    this.showDetailModal = true;
  }

  onModalClose(reload: boolean = false) {
    this.showFormModal = false;
    this.showDetailModal = false;
    this.selectedTask = null;
    if (reload) {
      this.loadTasks();
    }
  }

  deleteTask(taskId: number) {
    if (confirm('Are you sure you want to delete this task?')) {
      this.taskService.deleteTask(taskId).subscribe(() => this.loadTasks());
    }
  }
}
