import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  product: Product = null;

  constructor(
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params['id'];
    this.productService.details(id).subscribe(
      data => {
        this.product = data;
      },
      err => {
        this.toastr.error(err.error.message, 'Fail', {timeOut: 3000,  positionClass: 'toast-top-center',});
        // this.router.navigate(['/']);
      }
    );
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.productService.update(id, this.product).subscribe(
      data => {
        this.toastr.success(data.message, 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
        this.router.navigate(['/list']);
      },
      err => {
        this.toastr.error(err.error.message, 'Fail', {timeOut: 3000,  positionClass: 'toast-top-center', });
        this.router.navigate(['/']);
      }
    );
  }
}
