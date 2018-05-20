import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/internal/operators';
import { environment } from '../environments/environment';
import { Book } from '../models/book.model';

@Injectable()
export class BookService {

  private url = `${environment.rest_endpoint}/books`;

  constructor(private http: Http) {
  }

  getAll(): Observable<Book[]> {
    return this.http.get(this.url).pipe(map(res => res.json() as Book[]));
  }

  get(id: number): Observable<Book> {
    return this.http.get(`${this.url}/${id}`).pipe(map(res => res.json() as Book));
  }

}

