import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Author} from "../../shared/model/author.model";
import {AuthorService} from "../../shared/service/author.service";

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.scss']
})
export class AuthorsComponent implements OnInit {

  authors: Observable<Author[]>;

  constructor(private authorService: AuthorService) { }

  ngOnInit() {
    this.authors = this.authorService.getAll();
  }

}
