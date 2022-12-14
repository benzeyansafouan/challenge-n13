import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainLayoutRoutingModule } from 'src/app/pages/main-layout/main-layout-routing.module';
import { UsersListComponent } from './components/users-list/users-list.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { UserActionBarComponent } from './components/user-action-bar/user-action-bar.component';
import {MatTableModule} from '@angular/material/table';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { UserFormComponent } from './components/user-form/user-form.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDialogModule} from '@angular/material/dialog';
import {MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';


@NgModule({
  declarations: [
    UsersListComponent,
    UserActionBarComponent,
    UserFormComponent
  ],
  exports: [
    UsersListComponent,
    UserActionBarComponent,
    UserFormComponent
  ],
  imports: [
    CommonModule,
    MainLayoutRoutingModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatMenuModule,
    MatIconModule,
    MatFormFieldModule,
    FormsModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatTooltipModule,
    MatDialogModule,
    MatNativeDateModule,
    MatInputModule
  ]
})
export class MainLayoutModule { }
