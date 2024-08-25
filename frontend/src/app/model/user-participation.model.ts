export class UserParticipation {
    public   name:string;
    public  id:number;
    public  averageParticipation:number;
    public userParticipation:number;

    constructor(name?: string,id?: number, averageParticipation?: number ,userParticipation?: number){
         this.id = id;
         this.name = name;
         this.averageParticipation =averageParticipation;
         this.userParticipation = userParticipation;
         
    }
}
