import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    // Check if the user is logged in, if not redirect to login page
    if (!StorageService.isLoggedIn()) {
      this.router.navigateByUrl('');
      return false;
    }
    return true;
  }
}
