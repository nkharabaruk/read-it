import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppComponent} from "./app.component";

import {RoutingModule} from "./routing.module";
import {BookService} from "./services/book.service";
import {AuthorService} from "./services/author.service";
import {AuthorCardComponent} from "./components/author-card/author-card.component";
import {BookCardComponent} from "./components/book-card/book-card.component";
import {AuthorsListPage} from "./pages/authors-list/authors-list.page";
import {HomePage} from "./pages/home/home.page";
import {BooksListPage} from "./pages/books-list/books-list.page";
import {AuthorPage} from "./pages/author/author.page";
import {BookPage} from "./pages/book/book.page";

@NgModule({
  declarations: [
    AppComponent,
    AuthorCardComponent,
    BookCardComponent,
    HomePage,
    BooksListPage,
    BookPage,
    AuthorsListPage,
    AuthorPage
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
