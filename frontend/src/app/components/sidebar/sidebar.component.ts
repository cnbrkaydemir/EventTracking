import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDto } from 'src/app/model/user-dto.component.model';
import { Users } from 'src/app/model/users.model';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/user-profile', title: 'User profile',  icon:'ni-single-02 text-yellow', class: '' },
    { path: '/event-list', title: 'Events',  icon:'ni-bullet-list-67 text-red', class: '' },
    { path: '/event-creator', title: 'New Event',  icon:'ni-planet text-blue', class: '' },
    
   ];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;
  public user:Users=new Users();

  constructor(private router: Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
   this.user=JSON.parse(<string>sessionStorage.getItem('userdetails'));
    
  }

  logout(){
    window.sessionStorage.removeItem("Authorization");
    window.sessionStorage.removeItem("userdetails");
    window.sessionStorage.removeItem("XSRF-TOKEN");
    this.router.navigate(['/login']);
    window.location.reload();
  }

}
