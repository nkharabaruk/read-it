import {Http} from "@angular/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs/Observable";
import {Book} from "../model/book.model";
import "rxjs/add/operator/map";
import {Injectable} from "@angular/core";

@Injectable()
export class BookService {

  private url: string = `${environment.rest_endpoint}/books`;

  constructor(private http: Http) {
  }

  getAll(): Observable<Book[]> {
    return this.http.get(this.url).map(res => res.json() as Book[]);
  }

  get(id: number): Observable<Book> {
    return this.http.get(`${this.url}/${id}`).map(res => res.json() as Book);
  }

}

