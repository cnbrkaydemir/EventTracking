import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/model/users.model';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
public user:Users;
public age:number;
public university:string;
public location:string;

  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(<string>sessionStorage.getItem('userdetails'));
    this.generateAgeAndUni();

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

}
