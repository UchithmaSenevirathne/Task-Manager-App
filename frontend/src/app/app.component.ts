import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SharedModule } from './shared/shared/shared.module';
import { MatIconModule } from '@angular/material/icon';
import { StorageService } from './auth/services/storage/storage.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SharedModule, MatIconModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';

  isLoggedIn: boolean = StorageService.isLoggedIn();

  constructor(private router: Router){}

  ngOnInit(){
    this.router.events.subscribe(event => {
      this.isLoggedIn = StorageService.isLoggedIn();
    })
  }

  logout(){
    StorageService.signout();
    this.router.navigateByUrl("");
  }
}
