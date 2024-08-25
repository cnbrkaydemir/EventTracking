import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Events } from 'src/app/model/events.model';
import { Users } from 'src/app/model/users.model';
import { DashboardService } from 'src/app/services/dashboard.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDto } from 'src/app/model/user-dto.component.model';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})

export class EventComponent implements OnInit {

public users:Users=new Users();
public model:Events=new Events();
public target:number;
public info:UserDto = new UserDto();

  constructor(private dashboardService:DashboardService,private route:ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.users=JSON.parse(<string>sessionStorage.getItem('userdetails'));
    
    this.route.paramMap.subscribe(params => { 
      this.target= Number(params.get('id')); 
  });

  this.dashboardService.displayEventUser(this.target).subscribe(
    responseData=>{
      this.model= <any>responseData.body;
      console.log(this.model);
      
    },
    err=>{
      console.log(err);
    }
  );
   

  }

  discardUser(id:number){
    Swal.fire({
      title: 'Are you sure about removing this user ?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        this.info.eventId=this.target;
        this.info.userIds= new Array(0);
        this.info.userIds.push(id);
      
        this.dashboardService.discardUser(this.info).subscribe(
          responseData=>{
            Swal.fire('Removed!', 'User removed successfully. Check the event-list', 'success').then(()=>{
              this.router.navigate(['event-list']);
            })
            
          },
          err=>{
            console.log(err);
          }
        )
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled', 'User still in the event.', 'error');
      }
    });
  }







  }





