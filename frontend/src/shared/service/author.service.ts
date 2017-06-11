import {Http} from "@angular/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs/Observable";
import {Author} from "../model/author.model";
import "rxjs/add/operator/map";
import {Injectable} from "@angular/core";

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

}

