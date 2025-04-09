import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth/auth.service';
import {MatSnackBar} from '@angular/material/snack-bar'
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  providers: [AuthService],
  imports: [SharedModule, MatIconModule, HttpClientModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  signupForm!: FormGroup;

  hidePassword: boolean = true;

  constructor(private fb: FormBuilder,
    private service:AuthService,
    private snackbar:MatSnackBar,
    private router:Router
  ){}

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

    const password = this.signupForm.get("password")?.value;
    const confirmPassword = this.signupForm.get("confirmPassword")?.value;

    if(password !== confirmPassword){
      this.snackbar.open("Passwords do not match!", "Close", {duration:5000, panelClass: 'error-snackbar'})
      return;
    }

    console.log(this.signupForm.value);
    this.service.signup(this.signupForm.value).subscribe((res) => {
      console.log("response", res);
      if(res.code === 201){
        this.snackbar.open("Signup successfull", "Close", {duration:5000});
        this.router.navigateByUrl("/login")
      }else{
        this.snackbar.open("Signup failed", "Close", {duration:5000, panelClass: 'error-snackbar'})
      }
    })
  }
}

