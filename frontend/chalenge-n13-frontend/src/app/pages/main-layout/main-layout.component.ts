import { Component } from '@angular/core';
import {UserInfoService} from 'src/app/services/user-info.service';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss']
})
export class MainLayoutComponent {

  constructor(private userInfoService:UserInfoService) {
  }

}
