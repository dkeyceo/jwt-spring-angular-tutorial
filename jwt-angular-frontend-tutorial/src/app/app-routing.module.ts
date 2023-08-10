import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { IndexComponent } from './index/index.component';
import { DetailsProductComponent } from './product/details-product/details-product.component';
import { EditProductComponent } from './product/edit-product/edit-product.component';
import { ListProductComponent } from './product/list-product/list-product.component';
import { NewProductComponent } from './product/new-product/new-product.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'list', component: ListProductComponent, canActivate: [guard], data: { expectedRole: ['admin', 'user'] } },
  { path: 'details/:id', component: DetailsProductComponent, canActivate: [guard], data: { expectedRole: ['admin', 'user'] }},
  { path: 'new-product', component: NewProductComponent , canActivate: [guard], data: { expectedRole: ['admin'] }},
  { path: 'edit/:id', component: EditProductComponent, canActivate: [guard], data: { expectedRole: ['admin'] }},
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
