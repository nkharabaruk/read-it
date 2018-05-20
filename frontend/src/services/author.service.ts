import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/internal/operators';
import { Author } from '../models/author.model';
import { Book } from '../models/book.model';

@Injectable()
export class AuthorService {

  private url = `${environment.rest_endpoint}/authors`;

  constructor(private http: Http) {
  }

  getAll(): Observable<Author[]> {
    return this.http.get(this.url).pipe(map(res => res.json() as Author[]));
  }

  get(id: number): Observable<Author> {
    return this.http.get(`${this.url}/${id}`).pipe(map(res => res.json() as Author));
  }

  getAuthorBooks(id: number): Observable<Book[]> {
    return this.http.get(`${this.url}/${id}/books`).pipe(map(res => res.json() as Book[]));
  }

}

