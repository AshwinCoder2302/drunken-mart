import { Injectable } from '@angular/core';
import { environment } from 'src/enviroment';

@Injectable({
  providedIn: 'root'
})
export class RestAPIService {

  constructor() { }

  public CATEGORY: any = {
    GET_ALL_CATEGORY: '/category'
  };

  public PRODUCT: any = {
    GET_ALL_PRODUCT: '/product/category',
    GET_ALL_PRODUCT_BY_SEARCH: '/product/search?searchKeyword='
  };

  getURL(endpoint: string): any {
    return environment.dev ? environment.devURL + endpoint : environment.localURL + endpoint;
  }

}
