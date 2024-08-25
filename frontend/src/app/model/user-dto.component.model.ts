export class UserDto{
    
    public  eventId:number;

    public userIds:number[];
    
    
    constructor(eventId?:number, userIds?:number[]){
            this.eventId=eventId;
            this.userIds=userIds;
            
        }
    
    
    
    
    }