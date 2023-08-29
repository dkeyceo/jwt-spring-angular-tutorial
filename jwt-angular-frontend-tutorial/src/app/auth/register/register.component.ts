import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NewUser } from 'src/app/models/new-user';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: NewUser;
  name: string;
  username: string;
  email: string;
  password: string;
  errMessage: string;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
  }

  onRegister(): void {
    this.newUser = new NewUser(this.name, this.username, this.email, this.password);
    this.authService.createUser(this.newUser).subscribe(
      data => {
        this.toastr.success(data.message, 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
        this.router.navigate(['/login']);
      },
      err => {
        this.errMessage = err.error.message;
        this.toastr.error(this.errMessage, 'Fail', {timeOut: 3000,  positionClass: 'toast-top-center',});
        console.log(err.error.message);
      }
    );
  }

}
