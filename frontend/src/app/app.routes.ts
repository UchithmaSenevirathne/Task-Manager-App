import { Routes } from '@angular/router';
import { LoginComponent } from './auth/components/login/login.component';
import { SignupComponent } from './auth/components/signup/signup.component';
import { AuthGuard } from './auth/services/auth/auth.guard';

export const routes: Routes = [
    {path:"",component:LoginComponent},
    {path:"register",component:SignupComponent},
    {
        path: "user",
        loadChildren: () => import("./modules/user/user.module").then(m => m.UserModule),
        canActivate: [AuthGuard]
    },
];
