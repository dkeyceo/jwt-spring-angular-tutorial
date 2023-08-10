import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productURL = 'http://localhost:8080/products/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.productURL + 'list');
  }

  public details(id: number): Observable<Product> {
    return this.httpClient.get<Product>(this.productURL + `details/${id}`);
  }

  public detailsName(name: string): Observable<Product> {
    return this.httpClient.get<Product>(this.productURL + `detailsname/${name}`);
  }

  public save(product: Product): Observable<any> {
    return this.httpClient.post<any>(this.productURL + 'create', product);
  }

  public update(id: number, product: Product): Observable<any> {
    return this.httpClient.put<any>(this.productURL + `update/${id}`, product);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.productURL + `delete/${id}`);
  }
}
