import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Users } from 'src/app/model/users.model';
import { FormsModule } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  authStatus: string;
  model = new Users();

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit() {
    
  }
  ngOnDestroy() {
  }

  validateUser(loginForm: NgForm) {
    
    this.loginService.validateLoginDetails(this.model).subscribe(
      responseData => {
        
        window.sessionStorage.setItem("Authorization",responseData.headers.get('Authorization'));
        this.model = <any> responseData.body;
        
        this.model.authStatus = 'AUTH';
        window.sessionStorage.setItem("userdetails",JSON.stringify(this.model));
      
        
        this.router.navigate(['dashboard']);
        loginForm.resetForm();
        Swal.fire('Success', `You are logged in as ${this.model.userName} ${this.model.userSurname}` , 'success');

      }, error => {
        console.log(error);
        loginForm.resetForm();
        this.router.navigate(['/login']);
        Swal.fire('Invalid Login', 'Wrong email or password', 'warning').then(()=>{
          window.location.reload();
        }
          
        )
        
        
        
      });

  }

  getCookie(name) {
    let cookie = {};
    document.cookie.split(';').forEach(function(el) {
      let [k,v] = el.split('=');
      cookie[k.trim()] = v;
    })
    return cookie[name];
  }

}
