import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RestAPIService } from './rest-api.service';
import { Observable } from 'rxjs';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient, private restApiService: RestAPIService) { }

  getAllProductById(categoryId: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.restApiService.getURL(this.restApiService.PRODUCT.GET_ALL_PRODUCT) + '/' + categoryId);
  }
}
