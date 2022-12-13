export class UserInfo{
  id:string='';
  firstName:string='';
  lastName:string='';
  arabicFirstName:string='';
  arabicLastName:string='';
  cin:string='';
  profession:string='';
  birthDate?:Date;
  cardType:CardType=CardType.A;
  userImage:string='';
  userQrCodeImage:string='';
}

export enum CardType{
  A='A',
  B='B'
}
