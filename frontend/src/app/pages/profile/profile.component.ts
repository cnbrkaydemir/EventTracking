import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Events } from 'src/app/model/events.model';
import { Users } from 'src/app/model/users.model';
import { DashboardService } from 'src/app/services/dashboard.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile-other',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit {

public model:Users=new Users();
public loggedIn:Users= new Users();
public target:number;
public events:Events[];
public age:number;
public location:string;
public university:string;

  constructor(private dashboardService:DashboardService,private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    
    this.route.paramMap.subscribe(params => { 
      this.target= Number(params.get('id')); 
  });

  this.loggedIn=JSON.parse(<string>sessionStorage.getItem('userdetails'));


  this.dashboardService.getUser(this.target).subscribe(
    responseData=>{
      this.model= <any>responseData.body;
      console.log(this.model);
      
    },
    err=>{
      console.log(err);
    }
  );
   
  this.getEvents();

  this.generateAgeAndUni();

  }

  async getEvents(){
    await  this.dashboardService.displayEvents(this.target).subscribe(
      responseData=>{
        console.log(responseData);
        
        this.events=<any>responseData.body;
        
    },
    err=>{
      console.log(err);
    });
  }

  generateAgeAndUni(){
    const arr1=['TED UNIVERSITY','METU','BILKENT','BOGAZICI','ITU','HARVARD','YALE','MIT','OXFORD','CAMBRIDGE','TOBB UNIVERSITY'];
    const arr2=['Ankara ,Turkey ','Istanbul , Turkey','Izmir , Turkey','Paris , France','Alabama , USA','Texas , USA','Detroit , USA','Boston , USA','New York , Usa','Toronto , Canada','Montreal , Canada'];
    this.university=arr1[this.getRandomInt(0,10)];
    this.location=arr2[this.getRandomInt(0,10)];
    this.age=this.getRandomInt(18,65);
  }

  getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

grantAdmin(){
  Swal.fire({
    title: 'Are you sure about making this user admin ?',
    text: 'This process is irreversible.',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes, go ahead.',
    cancelButtonText: 'No, let me think',
  }).then((result) => {
    if (result.value) {
      
      this.dashboardService.grantAdmin(this.target).subscribe(
        responseData=>{
          Swal.fire('Success', 'User has become admin !', 'success').then(()=>{
            this.router.navigate(['/dashboard']);
          })
          
        },
        err=>{
          console.log(err);
        }
      )
    } else if (result.dismiss === Swal.DismissReason.cancel) {
      Swal.fire('Cancelled', 'User has not become an admin !', 'error');
    }
  });
}

  
  }





