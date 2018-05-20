import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../../../models/author.model';
import { AuthorService } from '../../../services/author.service';

@Component({
  templateUrl: './authors-list.page.html',
  styleUrls: ['./authors-list.page.scss']
})
export class AuthorListPage implements OnInit {

  authors: Observable<Author[]>;

  constructor(private authorService: AuthorService) {
  }

  ngOnInit() {
    this.authors = this.authorService.getAll();
  }

}
