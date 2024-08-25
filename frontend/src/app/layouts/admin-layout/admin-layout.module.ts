import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClipboardModule } from 'ngx-clipboard';

import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EventCreatorComponent } from 'src/app/pages/event-creator/event-creator.component';
import { EventComponent } from 'src/app/pages/event/event.component';
import { EventListComponent } from 'src/app/pages/event-list/event-list.component';
import { UserListComponent } from 'src/app/pages/user-list/user-list.component';
import { ProfileComponent } from 'src/app/pages/profile/profile.component';
import { CalendarModule } from '@syncfusion/ej2-angular-calendars';

// import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    ClipboardModule,
    CalendarModule
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    TablesComponent,
    IconsComponent,
    MapsComponent,
    EventCreatorComponent,
    EventComponent,
    ProfileComponent,
    EventListComponent,
    UserListComponent,
    
  ]
})

export class AdminLayoutModule {}
