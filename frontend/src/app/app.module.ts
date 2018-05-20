import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HomePage } from './pages/home/home.page';
import { BookListPage } from './pages/books-list/book-list-page.component';
import { AuthorListPage } from './pages/authors-list/author-list-page.component';
import { BookPage } from './pages/book/book.page';
import { AuthorPage } from './pages/author/author.page';
import { AuthorCardComponent } from './components/author-card/author-card.component';
import { BookCardComponent } from './components/book-card/book-card.component';
import { RoutingModule } from './routing.module';
import { AuthorService } from '../services/author.service';
import { BookService } from '../services/book.service';
import { Http, HttpModule } from '@angular/http';

@NgModule({
  declarations: [
    AppComponent,
    AuthorCardComponent,
    BookCardComponent,
    HomePage,
    AuthorListPage,
    AuthorPage,
    BookListPage,
    BookPage
  ],
  imports: [
    HttpModule,
    BrowserModule,
    RoutingModule
  ],
  providers: [
    AuthorService,
    BookService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
