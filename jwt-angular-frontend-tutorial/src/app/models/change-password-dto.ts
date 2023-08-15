export class ChangePasswordDto {
  password: string;
  confirmPassword: string;
  tokenPassword: string;

  constructor(password: string, confirmPassword: string,tokenPassword: string) {

  }
}
