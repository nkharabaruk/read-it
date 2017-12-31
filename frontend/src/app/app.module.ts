import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppComponent} from "./app.component";
import {HomeComponent} from "./home/home.component";
import {BooksComponent} from "./books/books.component";
import {AuthorsComponent} from "./authors/authors.component";
import {RoutingModule} from "./routing.module";
import {BookService} from "../shared/service/book.service";
import {AuthorService} from "../shared/service/author.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BooksComponent,
    AuthorsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RoutingModule,
    NgbModule.forRoot()
  ],
  providers: [
    AuthorService,
    BookService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
