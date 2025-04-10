import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export enum Status {
  TO_DO = 'TO_DO',
  IN_PROGRESS = 'IN_PROGRESS',
  DONE = 'DONE'
}

export interface Task {
  taskId?: number;
  title: string;
  description: string;
  status: Status;
  createdAt?: string;
  userId: number;
}

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl = 'http://localhost:8080/backend/task';

  constructor(private http: HttpClient) { }

  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}/all_tasks`);
  }

  createTask(task: Task): Observable<any> {
    return this.http.post(`${this.apiUrl}`, task);
  }

  updateTask(taskId: number, task: Task): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${taskId}`, task);
  }

  deleteTask(taskId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${taskId}`);
  }

  getTaskById(taskId: number): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/get/${taskId}`);
  }
}
