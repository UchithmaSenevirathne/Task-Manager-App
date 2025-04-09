import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared/shared.module';
import { MatIconModule } from '@angular/material/icon';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [SharedModule, MatIconModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm!: FormGroup;

  hidePassword: boolean = true;

  constructor(private fb: FormBuilder){}

  ngOnInit(){
    this.loginForm = this.fb.group({
      username:[null,[Validators.required, Validators.email]],
      password:[null,[Validators.required]],
    })
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  } 

  signIn(){
    console.log(this.loginForm.value)
  }
}
