import {HttpClient} from '@angular/common/http';


export abstract class ApiAbstractService {

  private API_URL: string;

  protected constructor(protected httpClient: HttpClient) {
   this.API_URL = 'http://localhost:8090'

  }

  protected generateUrl(additionals?: string[]): string {
    if (additionals) {
      return this.API_URL.concat('/').concat(additionals.join('/'));
    }
    return this.API_URL;
  }
}
