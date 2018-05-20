import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../../../models/book.model';
import { BookService } from '../../../services/book.service';

@Component({
  templateUrl: './books-list.page.html',
  styleUrls: ['./books-list.page.scss']
})
export class BookListPage implements OnInit {

  books: Observable<Book[]>;

  constructor(private bookService: BookService) {
  }

  ngOnInit() {
    this.books = this.bookService.getAll();
  }

}
