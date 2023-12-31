import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  isLogged = false;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    // private toastr: ToastrService
  ) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  onRegister(): void {
    this.newUser = new NewUser(this.name, this.username, this.email, this.password);
    this.authService.createUser(this.newUser).subscribe(
      data => {
        // this.toastr.success('Cuenta Creada', 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
        this.router.navigate(['/login']);
      },
      err => {
        this.errMessage = err.error.message;
        // this.toastr.error(this.errMsj, 'Fail', {timeOut: 3000,  positionClass: 'toast-top-center',});
        console.log(err.error.message);
      }
    );
  }

}
