import { Component, OnInit } from '@angular/core';
import { Carousel } from 'bootstrap';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit{

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
    const carouselElement = document.getElementById('carouselExampleIndicators');
    if (carouselElement) {
      const carousel = new Carousel(carouselElement, {
        interval: 2000,
        ride: 'carousel'
      });
    }
  }

}
