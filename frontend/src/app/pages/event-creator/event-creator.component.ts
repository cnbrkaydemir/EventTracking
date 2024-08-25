import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Events } from 'src/app/model/events.model';
import { Users } from 'src/app/model/users.model';
import { DashboardService } from 'src/app/services/dashboard.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-event-creator',
  templateUrl: './event-creator.component.html',
  styleUrls: ['./event-creator.component.scss']
})
export class EventCreatorComponent implements OnInit {
public model:Events=new Events();
public adminUser:Users;

  constructor(private dashboardService:DashboardService, private router:Router) { }

  ngOnInit() {
    this.adminUser=JSON.parse(<string>sessionStorage.getItem('userdetails'));
  }

  newEvent(eventForm:NgForm){
    this.model.created_by=this.adminUser;
    this.dashboardService.createEvent(this.model).subscribe(
      responseData=>{
        this.model=<any>responseData.body;
        eventForm.resetForm();
        Swal.fire({
          title: 'Successfully Created New Event',
          text: 'Now you can start adding new users',
          icon: 'success',
          confirmButtonText: 'Redirect to events'
        })
        .then(()=>{
          this.router.navigate(['/event-list']);
      })
        
        
    },
    err=>{
      console.log(err);
    });
  }

  }


