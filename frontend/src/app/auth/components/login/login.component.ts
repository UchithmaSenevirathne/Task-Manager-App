import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared/shared.module';
import { MatIconModule } from '@angular/material/icon';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import {MatSnackBar} from '@angular/material/snack-bar'
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { StorageService } from '../../services/storage/storage.service';

@Component({
  selector: 'app-login',
  standalone: true,
  providers: [AuthService],
  imports: [SharedModule, MatIconModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm!: FormGroup;

  hidePassword: boolean = true;

  constructor(private fb: FormBuilder,
    private service:AuthService,
    private snackbar:MatSnackBar,
    private router:Router
  ){}

  ngOnInit(){
    this.loginForm = this.fb.group({
      username:[null,[Validators.required, Validators.email]],
      password:[null,[Validators.required]],
    })
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  } 

  login(){
    console.log(this.loginForm.value)
    this.service.login(this.loginForm.value).subscribe((res) => {
      console.log("response", res);
      if(res.code === 201){
        console.log(res)
        
        StorageService.saveUserName(res.data.username);
        StorageService.saveToken(res.data.token)

        this.router.navigateByUrl("/user/task");
        
      }else{
        this.snackbar.open("Invalid Credentials", "Close", {duration:5000, panelClass: 'error-snackbar'})
      }
    })
  }
}
