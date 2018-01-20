import { Component, OnInit } from '@angular/core';
import {BookService} from "../../services/book.service";
import {Observable} from "rxjs/Observable";
import {Book} from "../../models/book.model";

@Component({
  templateUrl: './books-list.page.html',
  styleUrls: ['./books-list.page.scss']
})
export class BooksListPage implements OnInit {

  books: Observable<Book[]>;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.books = this.bookService.getAll();
  }

}
