import { Injectable } from '@angular/core';
import { Category } from '../model/category';
import { HttpClient } from '@angular/common/http';
import { RestAPIService } from './rest-api.service';
import { Observable } from 'rxjs';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient, private restApiService: RestAPIService) { }

  getAllCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(this.restApiService.getURL(this.restApiService.CATEGORY.GET_ALL_CATEGORY));
  }

  getAllProductByCategoryId(categoryId: string | null): Observable<Product[]> {
    return this.http.get<Product[]>(this.restApiService.getURL(this.restApiService.CATEGORY.GET_ALL_CATEGORY) + '/' + categoryId);
  }
}
