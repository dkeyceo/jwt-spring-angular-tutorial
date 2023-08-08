import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-details-product',
  templateUrl: './details-product.component.html',
  styleUrls: ['./details-product.component.css']
})
export class DetailsProductComponent implements OnInit {

  product: Product = null;

  constructor(
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    // private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params['id'];
    this.productService.details(id).subscribe(
      data => {
        this.product = data;
      },
      err => {
        // this.toastr.error(err.error.mensaje, 'Fail', {timeOut: 3000,  positionClass: 'toast-top-center',});
        this.backToList();
      }
    );
  }

  backToList(): void {
    this.router.navigate(['/list']);
  }

}
