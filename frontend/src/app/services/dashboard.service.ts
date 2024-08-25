import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AppConstants } from '../constants/constants';
import { Events } from '../model/events.model';
import { UserDto } from '../model/user-dto.component.model';
import { Users } from '../model/users.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http:HttpClient) { }


  createEvent(event : Events){
    return this.http.post(environment.rooturl + AppConstants.CREATE_EVENT_API_URL,event,{ observe: 'response',withCredentials: true });
  }

  addUser(info:UserDto){
    return this.http.post(environment.rooturl + AppConstants.ADD_USER_API_URL,info,{ observe: 'response',withCredentials: true });
  }

  discardUser(info:UserDto){
    return this.http.post(environment.rooturl + AppConstants.DISCARD_USER_API_URL,info,{ observe: 'response',withCredentials: true });
  }

  getUser(id : number){
    return this.http.post(environment.rooturl + AppConstants.DISPLAY_USER_API_URL,id,{ observe: 'response',withCredentials: true });
  }

  displayEvents(id:number){
    return this.http.post(environment.rooturl + AppConstants.DISPLAY_EVENTS_API_URL,id,{ observe: 'response',withCredentials: true });
  }

  displayAllEvents(){
    return this.http.get(environment.rooturl + AppConstants.DISPLAY_ALL_EVENT_API_URL);
  }


 register(user : Users){
    return this.http.post(environment.rooturl + AppConstants.REGISTER_API_URL,user,{ observe: 'response',withCredentials: true });
  }

  displayAllUsers(){
    return this.http.get(environment.rooturl + AppConstants.DISPLAY_ALL_USER_API_URL);
  }

  displayEventUser(id:number){
    return this.http.post(environment.rooturl + AppConstants.DISPLAY_EVENT_USER_API_URL,id,{ observe: 'response',withCredentials: true });
  }

  displayAbsent(id:number){
    return this.http.post(environment.rooturl + AppConstants.DISPLAY_ABSENT_API_URL,id,{ observe: 'response',withCredentials: true });
  }

  getMonth(id:number){
    return this.http.post(environment.rooturl + AppConstants.DISPLAY_MONTH_API_URL,id,{ observe: 'response',withCredentials: true });
  }

  getUpcoming(id:number){
    return this.http.post(environment.rooturl + AppConstants.DISPLAY_UPCOMING_API_URL,id,{ observe: 'response',withCredentials: true });
  }

  grantAdmin(id:number){
    return this.http.post(environment.rooturl + AppConstants.GRANT_ADMIN_API_URL,id,{ observe: 'response',withCredentials: true });
  }

}
