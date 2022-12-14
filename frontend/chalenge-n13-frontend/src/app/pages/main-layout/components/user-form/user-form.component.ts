import { Component, ElementRef, ViewChild} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {MatDialogRef} from '@angular/material/dialog';
import {UserInfoService} from 'src/app/services/user-info.service';
import {UserInfo} from 'src/app/models/UserInfo.model';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent {
  isLoading:boolean=false;
  filePreview: string | undefined;
  form!: FormGroup;
  user:UserInfo
  // @ts-ignore
  @ViewChild('UploadFileInput') uploadFileInput: ElementRef | undefined;
  userImageName = 'Select File';
  userImage: File | undefined;


  constructor(public dialogRef: MatDialogRef<UserFormComponent>,
              private userInfoService:UserInfoService) {
    this.user=new UserInfo();
  }

  onClose(){
    this.dialogRef.close();
  }
  onSave(){
    this.addNewCard();
    this.dialogRef.close();
  }

  fileChangeEvent(fileInput: any) {
    if (fileInput.target.files && fileInput.target.files[0]) {
      const file: File = fileInput.target.files[0];
      this.userImage = file;
      this.userImageName = this.userImage.name;
      const reader = new FileReader();
      reader.onload = () => {
        this.filePreview = reader.result as string;
      }
      reader.readAsDataURL(file)
    } else {
      this.userImageName = 'Select_file';
    }
  }

  addNewCard(){
    this.userInfoService.saveUserInfo(this.user,this.userImage).subscribe(res=> {
      const filename: string = 'test.pdf';
      const blob = new Blob([res.body], {type: 'text/plain'});
      saveAs(blob, filename);
    });
  }

}
