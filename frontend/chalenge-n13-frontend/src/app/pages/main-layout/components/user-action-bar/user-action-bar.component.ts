import { Component } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {UserFormComponent} from 'src/app/pages/main-layout/components/user-form/user-form.component';

@Component({
  selector: 'app-user-action-bar',
  templateUrl: './user-action-bar.component.html',
  styleUrls: ['./user-action-bar.component.scss']
})
export class UserActionBarComponent {

  search:string='';
  constructor(public dialog: MatDialog) {
  }




  onSearch(){}
  onAddNewCard(){
    this.dialog.open(UserFormComponent, {
      width: '800px'
    });
  }

}
