import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { provideRouter, Routes } from '@angular/router';
import { TaskListComponent } from './components/task/task-list/task-list/task-list.component';

const routes: Routes = [
  {path: "task", component: TaskListComponent}
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [provideRouter(routes)]
})
export class UserModule { }
