import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  searchKeyword: string = '';

  constructor(private router: Router, private searchService: SearchService) { }

  navigateToProductComponent(): void {
    console.log("Entered into navigate=>" + this.searchKeyword)
    this.searchService.setSearchKeyword(this.searchKeyword);
    this.router.navigate(['/home/product']);
  }

}
