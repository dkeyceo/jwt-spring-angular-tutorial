import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChangePasswordDto } from 'src/app/models/change-password-dto';
import { EmailPasswordService } from 'src/app/services/email-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  password: string;
  confirmPassword: string;
  tokenPassword: string;

  dto: ChangePasswordDto;

  constructor(
    private emailPasswordService: EmailPasswordService,
    // private toastrService: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }

  onChangePassword(): void {
    if(this.password !== this.confirmPassword) {
      alert("Passwords not equal")
      // this.toastrService.error('Las contraseñas no coinciden', 'FAIL', {timeOut: 3000, positionClass: 'toast-top-center'});
      return;
    }
    this.tokenPassword = this.activatedRoute.snapshot.params['tokenPassword'];
    this.dto = new ChangePasswordDto(this.password, this.confirmPassword, this.tokenPassword);
    this.emailPasswordService.changePassword(this.dto).subscribe(
      data => {
        // this.toastrService.success(data.mensaje, 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
        this.router.navigate(['/login']);
    },
    err => {
      // this.toastrService.error(err.error.mensaje, 'FAIL', {timeOut: 3000, positionClass: 'toast-top-center'});
      alert('Fail');
    }
    );
  }

}
