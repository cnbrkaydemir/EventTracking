import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { Events } from 'src/app/model/events.model';
import { Users } from 'src/app/model/users.model';
import { DashboardService } from 'src/app/services/dashboard.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { UserDto } from 'src/app/model/user-dto.component.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})

export class UserListComponent implements OnInit {
public  form: FormGroup;
public target:number;
public model:Events;
public eventUser:Users[];
public allUsers:Users[];
public dto:UserDto= new UserDto();

  constructor(private dashboardService:DashboardService,private route:ActivatedRoute,private fb: FormBuilder,private router:Router) { 
    this.form = fb.group({
      selectedUsers:  new FormArray([])
     });
  }

  ngOnInit() {
    
    this.route.paramMap.subscribe(params => { 
      this.target= Number(params.get('id')); 
  });

this.getAbsent(this.target);

  }

  onCheckboxChange(event: any) {
    const selectedUsers = (this.form.controls['selectedUsers'] as FormArray);
    if (event.target.checked) {
      selectedUsers.push(new FormControl(event.target.value));
    } else {
      const index = selectedUsers.controls
      .findIndex(x => x.value === event.target.value);
      selectedUsers.removeAt(index);
  }
}

  getAbsent(target:number){
  this.dashboardService.displayAbsent(target).subscribe(
    responseData=>{
      this.allUsers=<any>responseData.body;
    }
  )
}

submit() {
  let arr:number[];
  arr=this.form.value.selectedUsers;
  this.dto.eventId=this.target;
  this.dto.userIds=arr;

  this.dashboardService.addUser(this.dto).subscribe(
    responseData=>{
      Swal.fire('Added', 'You have added specified users to this event', 'success').then(()=>{
        this.router.navigate(['event-list']);
      })
      
    },
    err=>{
      console.log(err);
      
    }
  )
}


}


