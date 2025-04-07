import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-signup',
  imports: [SharedModule, MatIconModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  signupForm!: FormGroup;

  hidePassword: boolean = true;

  constructor(private fb: FormBuilder){}

  ngOnInit(){
    this.signupForm = this.fb.group({
      username:[null,[Validators.required, Validators.email]],
      password:[null,[Validators.required]],
      confirmPassword:[null,[Validators.required]],
    })
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  } 

  signup(){
    console.log(this.signupForm.value)
  }
}

