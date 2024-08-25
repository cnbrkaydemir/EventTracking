export class EventInfo {

    public  title:string;

    public dueDate:number;

    public id:number;

    public  eventExpire:Date;

    constructor(title?:string, dueDate?:number, id?:number,eventExpire?:Date) {
        this.title = title;
        this.dueDate = dueDate;
        this.id = id;
        this.eventExpire=eventExpire;
    }

}