import { Component, OnInit } from '@angular/core';
import {BookService} from "../../shared/service/book.service";
import {Observable} from "rxjs/Observable";
import {Book} from "../../shared/model/book.model";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {

  books: Observable<Book[]>;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.books = this.bookService.getAll();
  }

}
