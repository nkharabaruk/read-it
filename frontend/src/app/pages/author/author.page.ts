import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/switchMap';
import {AuthorService} from "../../services/author.service";
import {Author} from "../../models/author.model";
import {Book} from "../../models/book.model";

@Component({
  templateUrl: './author.page.html',
  styleUrls: ['./author.page.scss']
})
export class AuthorPage implements OnInit {

  id: number;
  author: Author = <Author>{};
  books: Observable<Book[]>;

  constructor(route: ActivatedRoute, private authorService: AuthorService) {
    this.id = parseInt(route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    this.authorService.get(this.id)
      .subscribe(author => this.author = author);
    this.books = this.authorService.getAuthorBooks(this.id);
  }

}
