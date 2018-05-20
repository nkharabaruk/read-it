import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HomePage } from './pages/home/home.page';
import { BookListPage } from './pages/books-list/book-list-page.component';
import { AuthorListPage } from './pages/authors-list/author-list-page.component';
import { AuthorPage } from './pages/author/author.page';
import { BookPage } from './pages/book/book.page';

const routes = [
  { path: '', component: HomePage },
  { path: 'books', component: BookListPage },
  { path: 'books/:id', component: BookPage },
  { path: 'authors', component: AuthorListPage },
  { path: 'authors/:id', component: AuthorPage }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule {
}
