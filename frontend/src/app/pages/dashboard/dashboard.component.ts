import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import Chart from 'chart.js';
import { EventInfo } from 'src/app/model/event-info.model';
import { Events } from 'src/app/model/events.model';
import { UserParticipation } from 'src/app/model/user-participation.model';
import { Users } from 'src/app/model/users.model';
import { DashboardService } from 'src/app/services/dashboard.service';

// core components
import {
  chartOptions,
  parseOptions,
  chartExample1,
  chartExample2
} from "../../variables/charts";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],

})
export class DashboardComponent implements OnInit {

  public datasets: any;
  public data: any;
  public salesChart;
  public clicked: boolean = true;
  public clicked1: boolean = false;
  public user:Users;
  public model:Events[];
  public allEvents :Events[];
  public upcomingMeeting:number=0;
  public PastMeeting:number=0;
  public monthCount=[];
  public userInfos:UserParticipation[];
  public upcomingEvents:EventInfo[];

  public months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
  ];



  constructor(private dashboardService:DashboardService,private cd: ChangeDetectorRef){

  }

  ngOnInit() {

    
    
    this.getEventList();

    
    this.getOrders();

    
    this.getAllEvents();

    this.getUserDetails();

    this.getUpcoming();

    

    parseOptions(Chart, chartOptions());

   

    
  }


  public updateOptions() {
    this.salesChart.data.datasets[0].data = this.data;
    this.salesChart.update();
  }

  async getEventList(){
    this.user=JSON.parse(<string>sessionStorage.getItem('userdetails'));

    
     this.dashboardService.displayEvents(this.user.userId).subscribe(
       (responseData)=>{
        console.log(responseData);
        
        this.model= <any> responseData.body;
        
        for(let event=0;this.model.length>event; ++event){
             
          if(this.model[event].expired===true){
            ++this.PastMeeting;
          }
          else{
            ++this.upcomingMeeting;
          }
        } 
    },
    err=>{
      console.log(err);
    });
  }


async getOrders(){
  await this.dashboardService.getMonth(this.user.userId).subscribe(
    responseData=>{
      console.log(responseData);
      
      this.monthCount=<any>responseData.body;
      
      let reference:Date=new Date();
  
   
      var chartOrders = document.getElementById('chart-orders');
    
      console.log(this.monthCount);
      
    
    
      var ordersChart = new Chart(chartOrders, {
        type: 'bar',
        options: chartExample2.options,
        data:  {
          labels: [this.months[reference.getMonth()], this.months[reference.getMonth()+1],this.months[reference.getMonth()+2], this.months[reference.getMonth()+3]],
          datasets: [
            {
              label: "Events",
              data: this.monthCount,
              maxBarThickness: 10
            }
          ]
        }
      });
         

    },
    err=>{
      console.log(err);
      
    }
  );

  
}
async getAllEvents(){
  
  this.dashboardService.displayAllEvents().subscribe(
    responseData=>{
      console.log(responseData);
      this.allEvents=<any>responseData;
    },
    err=>{
      console.log(err);      
    }
  )
 
    
}

async getUserDetails(){
this.dashboardService.displayAllUsers().subscribe(
  responseData=>{
    this.userInfos=<any> responseData;
    console.log(this.userInfos);
    
  },
  err=>{
    console.log(err);
    
  }
)

}


async getUpcoming(){
  this.user=JSON.parse(<string>sessionStorage.getItem('userdetails'));

  await this.dashboardService.getUpcoming(this.user.userId).subscribe(
    responseData=>{

      let ref= new Date();
            
      this.upcomingEvents=<any>responseData.body;
      
      
    },
    err=>{
      console.log(err);
      
    }
  )
}


}


