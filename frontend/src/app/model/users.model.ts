export class Users{

    public userId: number|undefined;
    public userName: string|undefined;
    public userSurname: string|undefined;
    public userEmail : string|undefined;
    public userPassword: string|undefined;
    public userRole : string|undefined;
    public events : Object[]|undefined;
    public authStatus : string|undefined;
    
    
    
    constructor(id?: number,name?: string, surname?: string ,email?: string,  password?: string,role?: string,
         authStatus?:string, events?:Object[]){
          this.userId = id;
          this.userName = name;
          this.userSurname = surname;
          this.userEmail = email;
          this.userPassword = password;
          this.userRole = role;
          this.authStatus = authStatus;
          this.events=events;
    }
    
    
    
    }

