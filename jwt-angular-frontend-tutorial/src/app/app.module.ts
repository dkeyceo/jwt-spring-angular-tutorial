import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { MenuComponent } from './menu/menu.component';
import { IndexComponent } from './index/index.component';
import { NewProductComponent } from './product/new-product/new-product.component';
import { EditProductComponent } from './product/edit-product/edit-product.component';
import { ListProductComponent } from './product/list-product/list-product.component';
import { DetailsProductComponent } from './product/details-product/details-product.component';
import { interceptorProvider } from './interceptors/prod-interceptor.service';
import { SendEmailComponent } from './changepassword/send-email/send-email.component';
import { ChangePasswordComponent } from './changepassword/change-password/change-password.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MenuComponent,
    IndexComponent,
    NewProductComponent,
    EditProductComponent,
    DetailsProductComponent,
    ListProductComponent,
    SendEmailComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
