import {Http} from "@angular/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs/Observable";
import {Author} from "../models/author.model";
import "rxjs/add/operator/map";
import {Injectable} from "@angular/core";
import {Book} from "../models/book.model";

@Injectable()
export class AuthorService {

  private url: string = `${environment.rest_endpoint}/authors`;

  constructor(private http: Http) {
  }

  getAll(): Observable<Author[]> {
    return this.http.get(this.url).map(res => res.json() as Author[]);
  }

  get(id: number): Observable<Author> {
    return this.http.get(`${this.url}/${id}`).map(res => res.json() as Author);
  }

  getAuthorBooks(id: number): Observable<Book[]> {
    return this.http.get(`${this.url}/${id}/books`).map(res => res.json() as Book[]);
  }

}

