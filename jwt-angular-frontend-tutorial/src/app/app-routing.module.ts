import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { IndexComponent } from './index/index.component';
import { DetailsProductComponent } from './product/details-product/details-product.component';
import { EditProductComponent } from './product/edit-product/edit-product.component';
import { ListProductComponent } from './product/list-product/list-product.component';
import { NewProductComponent } from './product/new-product/new-product.component';
import { ProdGuardService} from './guards/prod-guard.service';
import { LoginGuard } from './guards/login.guard';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [LoginGuard] },
  { path: 'list', component: ListProductComponent, canActivate: [ProdGuardService], data: { expectedRole: ['admin', 'user'] } },
  { path: 'details/:id', component: DetailsProductComponent, canActivate: [ProdGuardService], data: { expectedRole: ['admin', 'user'] }},
  { path: 'new-product', component: NewProductComponent , canActivate: [ProdGuardService], data: { expectedRole: ['admin'] }},
  { path: 'edit/:id', component: EditProductComponent, canActivate: [ProdGuardService], data: { expectedRole: ['admin'] }},
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
