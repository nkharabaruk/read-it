import {Component, Input, OnInit} from '@angular/core';
import {Author} from "../../model/author.model";

@Component({
  selector: 'author-card',
  templateUrl: './author-card.component.html',
  styleUrls: ['./author-card.component.scss']
})
export class AuthorCardComponent implements OnInit {

  @Input() author: Author;

  constructor() {
  }

  ngOnInit() {
  }

}
