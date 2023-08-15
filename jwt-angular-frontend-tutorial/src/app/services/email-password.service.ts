import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ChangePasswordDto } from '../models/change-password-dto';
import { EmailValuesDto } from '../models/email-values-dto';

@Injectable({
  providedIn: 'root'
})
export class EmailPasswordService {

  changePasswordURL = environment.changePasswordURL;

  constructor(private httpClient: HttpClient) { }

  public sendEmail(dto: EmailValuesDto): Observable<any> {
    return this.httpClient.post<any>(this.changePasswordURL + 'send-email', dto);
  }

  public changePassword(dto: ChangePasswordDto): Observable<any> {
    return this.httpClient.post<any>(this.changePasswordURL + 'change-password', dto);
  }
}
