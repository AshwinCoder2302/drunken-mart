import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { Product } from 'src/app/model/product';

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
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    const categoryId = this.route.snapshot.paramMap.get('id');
    this.categoryService.getAllProductByCategoryId(categoryId).subscribe(products => {
      this.products = products;
    });
  }

  roundPrice(price: number, discount: number): any {
    return Math.round(price - (price * (discount / 100)));
  }
}
