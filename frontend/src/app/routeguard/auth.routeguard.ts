import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot,Router } from '@angular/router';
import { Users } from '../model/users.model';

@Injectable()
export class AuthActivateRouteGuard implements CanActivate {
    user = new Users();
    
    constructor(private router: Router){

    }

    canActivate(route:ActivatedRouteSnapshot, state:RouterStateSnapshot){
        this.user = JSON.parse(<string>sessionStorage.getItem('userdetails'));
        if(!this.user){
            this.router.navigate(['login']);
        }
        return this.user?true:false;
    }

}