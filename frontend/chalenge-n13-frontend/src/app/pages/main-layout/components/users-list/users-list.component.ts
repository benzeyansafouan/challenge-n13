import { Component } from '@angular/core';
import {UserInfo} from 'src/app/models/UserInfo.model';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent {

  displayedColumns: string[] = ['firstName', 'lastName', 'cin', 'profession', 'birthDate', 'cardType', 'actions'];
  $users: UserInfo[];
  dataSource: MatTableDataSource<UserInfo>;
  $isLoading:boolean=false;

  constructor() {
    this.$users=[];
    this.$users.sort((a, b) => a.firstName.localeCompare(b.firstName));
    this.dataSource = new MatTableDataSource<UserInfo>(this.$users);
  }

  onEdit(user: UserInfo){}
  onDelete(user: UserInfo){}

}
