import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class SearchService {
    private searchKeywordSource = new BehaviorSubject<string>('');
    searchKeyword$ = this.searchKeywordSource.asObservable();

    setSearchKeyword(keyword: string) {
        this.searchKeywordSource.next(keyword);
    }
}
