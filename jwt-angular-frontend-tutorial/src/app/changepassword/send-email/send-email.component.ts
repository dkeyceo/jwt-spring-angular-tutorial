import { Component, OnInit } from '@angular/core';
import { EmailValuesDto } from 'src/app/models/email-values-dto';
import { EmailPasswordService } from 'src/app/services/email-password.service';

@Component({
  selector: 'app-send-email',
  templateUrl: './send-email.component.html',
  styleUrls: ['./send-email.component.css']
})
export class SendEmailComponent implements OnInit {

  mailTo: string;
  dto: EmailValuesDto;

  constructor(
    private emailPasswordService: EmailPasswordService,
    // private toastrService: ToastrService
  ) { }

  ngOnInit() {
  }

  onSendEmail(): void {
    this.dto = new EmailValuesDto(this.mailTo);
    this.emailPasswordService.sendEmail(this.dto).subscribe(
      data => {
        alert('Success');
          // this.toastrService.success(data.mensaje, 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
      },
      err => {
        console.log(err.error.message);
        // this.toastrService.error(err.error.mensaje, 'FAIL', {timeOut: 3000, positionClass: 'toast-top-center'});
      }
    );
  }
}
