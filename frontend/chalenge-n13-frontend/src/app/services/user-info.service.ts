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

  public saveUserInfo(userInfo:UserInfo,userImage: File | undefined):Observable<any>{
    let formData = new FormData();
    formData.append('userInfo', JSON.stringify(userInfo));
    if (userImage) {
      formData.append('userImage', userImage);
    }
    return this.httpClient.post(this.generateUrl(['user','save']),formData,{
      observe: 'response',
      responseType: 'arraybuffer'
    });
  }

  public getUsers():Observable<UserInfo[]>{
    return this.httpClient.get<UserInfo[]>(this.generateUrl(['user','get-users']));
  }
}
