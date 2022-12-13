import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainLayoutRoutingModule } from 'src/app/pages/main-layout/main-layout-routing.module';
import { UsersListComponent } from './users-list/users-list.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';


@NgModule({
  declarations: [
    UsersListComponent
  ],
  exports: [
    UsersListComponent
  ],
  imports: [
    CommonModule,
    MainLayoutRoutingModule,
    MatProgressSpinnerModule
  ]
})
export class MainLayoutModule { }
