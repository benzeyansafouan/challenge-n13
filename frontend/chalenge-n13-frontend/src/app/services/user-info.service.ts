import { Injectable } from '@angular/core';
import {ApiAbstractService} from 'src/app/services/api-abstract.service';
import {HttpClient} from '@angular/common/http';
import {UserInfo} from 'src/app/models/UserInfo.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserInfoService extends ApiAbstractService{

  constructor(protected override httpClient: HttpClient) {
    super(httpClient);
  }

  public saveUserInfo(userInfo:UserInfo):Observable<UserInfo>{
    return this.httpClient.post<UserInfo>(this.generateUrl(['user','save']),userInfo);
  }

  public getUsers():Observable<UserInfo[]>{
    return this.httpClient.get<UserInfo[]>(this.generateUrl(['user','get-users']));
  }
}
