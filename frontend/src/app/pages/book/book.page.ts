import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../../models/book.model';
import { BookService } from '../../../services/book.service';

@Component({
  templateUrl: './book.page.html',
  styleUrls: ['./book.page.scss']
})
export class BookPage implements OnInit {

  id: number;
  book: Book = <Book>{};

  constructor(route: ActivatedRoute, private bookService: BookService) {
    this.id = parseInt(route.snapshot.paramMap.get('id'), 10);
  }

  ngOnInit() {
    this.bookService.get(this.id)
      .subscribe(book => this.book = book);
  }

}
