import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { provideRouter, Routes } from '@angular/router';
import { TaskComponent } from './components/task/task.component';

const routes: Routes = [
  {path: "task", component: TaskComponent}
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [provideRouter(routes)]
})
export class UserModule { }
