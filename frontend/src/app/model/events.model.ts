import { Users } from "./users.model";

export class Events{
    
    public eventId:number|undefined;
    public eventTitle:string|undefined;
    public eventDescription:string|undefined;
    public eventDate:Date|undefined;
    public eventExpired:Date|undefined;
    public created_by:Users|undefined;
    public participants:Object[]|undefined;
    public expired:boolean;
    public dueDate:number;
    
    
    constructor(eventId?:number, eventTitle?:string, eventDesc?:string, eventDate?:Date,eventExpire?:Date,
        created_by?:Users,users?:Object[],dueDate?:number){
            this.eventId=eventId;
            this.eventTitle=eventTitle;
            this.eventDescription=eventDesc;
            this.eventDate=eventDate;
            this.eventExpired=eventExpire;
            this.created_by=created_by;
            this.participants=users;
            this.expired=false;
            this.dueDate=dueDate;
        
        }

        
    
    
    
    
    }