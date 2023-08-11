import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {

  products: Product[] = [];
  isAdmin = false;

  constructor(
    private productService: ProductService,
    // private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.getProducts();
    this.isAdmin = this.tokenService.isAdmin();
  }

  getProducts(): void {
    this.productService.list().subscribe(
      data => {
        this.products = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  delete(id: number) {
    this.productService.delete(id).subscribe(
      data => {
        // this.toastr.success('Producto Eliminado', 'OK', {timeOut: 3000, positionClass: 'toast-top-center' });
        this.getProducts();
      },
      err => {
        // this.toastr.error(err.error.mensaje, 'Fail', {timeOut: 3000, positionClass: 'toast-top-center', });
        console.log(err.error.message)
      }
    );
  }
}
