import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  name = '';
  price: number = null;

  constructor(
    private productService: ProductService,
    // private toastr: ToastrService,
    private router: Router
    ) { }

  ngOnInit() {
  }

  onCreate(): void {
    const product = new Product(this.name, this.price);
    this.productService.save(product).subscribe(
      data => {
        // this.toastr.success('Producto Creado', 'OK', {timeOut: 3000, positionClass: 'toast-top-center' });
        this.router.navigate(['/list']);
      },
      err => {
        // this.toastr.error(err.error.mensaje, 'Fail', {timeOut: 3000,  positionClass: 'toast-top-center',});
         this.router.navigate(['/']);
         console.log(err.error.message);
      }
    );
  }
}
