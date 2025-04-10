import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Task } from '../../../../../../auth/services/task/task.service';

@Component({
  selector: 'app-task-detail',
  template: `
    <div class="modal show d-block" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Task Details</h5>
            <button type="button" class="btn-close" (click)="close.emit()"></button>
          </div>
          <div class="modal-body">
            <p><strong>ID:</strong> {{ task.taskId }}</p>
            <p><strong>Title:</strong> {{ task.title }}</p>
            <p><strong>Description:</strong> {{ task.description }}</p>
            <p><strong>Status:</strong> {{ task.status }}</p>
            <p><strong>Created At:</strong> {{ task.createdAt }}</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn border-secondary" (click)="close.emit()">Close</button>
          </div>
        </div>
      </div>
    </div>
  `
})
export class TaskDetailComponent {
  @Input() task!: Task;
  @Output() close = new EventEmitter<void>();
}
