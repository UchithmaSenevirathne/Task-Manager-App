import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Task } from '../../../../../../auth/services/task/task.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-task-detail',
  imports: [CommonModule],
  templateUrl: './task-detail.component.html',
})
export class TaskDetailComponent {
  @Input() task!: Task;
  @Output() close = new EventEmitter<void>();

  // Method to get background color based on task status
  getStatusColor(status: string): string {
    switch (status) {
      case 'TO_DO':
        return '#d5aaee';  // Light purple for TO_DO
      case 'IN_PROGRESS':
        return '#b28dff';  // Purple for IN_PROGRESS
      case 'DONE':
        return '#97a2ff';  // Light blue for DONE
      default:
        return '#ffffff';  // Default color if no match
    }
  }
}
