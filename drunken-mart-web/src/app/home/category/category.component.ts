import { Component, OnInit } from '@angular/core';
import { Carousel } from 'bootstrap';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoryService, private productService: ProductService, private router: Router) { }

  slides: { image: string, caption: string }[] = [
    { image: 'assets/carousel1.png', caption: 'Sports' },
    { image: 'assets/carousel2.png', caption: 'Mats' },
    { image: 'assets/carousel3.png', caption: 'SmartPhones' },
    { image: 'assets/carousel4.png', caption: 'Toys' },
    { image: 'assets/carousel5.png', caption: 'Skin Care' },
    { image: 'assets/carousel6.png', caption: 'Clothes' },
    { image: 'assets/carousel7.png', caption: 'TShirts' },
  ];

  ngOnInit(): void {
    this.getAllCategories();
    const carouselElement = document.getElementById('carouselExampleIndicators');
    if (carouselElement) {
      const carousel = new Carousel(carouselElement, {
        interval: 2000,
        ride: 'carousel'
      });
    }
  }

  getAllCategories(): void {
    this.categoryService.getAllCategory().subscribe(categories => {
      this.categories = categories;
    });
  }

  navigateToProductComponent(category: Category): void {
    this.router.navigate(['/home/product', category.id]);
  }
}
