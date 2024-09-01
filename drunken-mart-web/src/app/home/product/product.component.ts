import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { Product } from 'src/app/model/product';
import { ProductService } from 'src/app/services/product.service';
import { SearchService } from 'src/app/services/search.service';

export interface Category {
  id: string;
  name: string;
  discount: number;
  discountType: string;
  description: string;
  image: string;
}

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[] = [];

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private productService: ProductService,
    private searchService: SearchService
  ) { }

  ngOnInit(): void {
    this.searchService.searchKeyword$.subscribe(keyword => {
      this.productService.getAllProductsBySearchKeyword(keyword).subscribe(products => {
        this.products = products;
      });
    });

    const categoryId = this.route.snapshot.paramMap.get('id');
    if (categoryId != null) {
      this.categoryService.getAllProductByCategoryId(categoryId).subscribe(products => {
        this.products = products;
      });
    };
  }

  roundPrice(price: number, discount: number): any {
    return Math.round(price - (price * (discount / 100)));
  }
}
