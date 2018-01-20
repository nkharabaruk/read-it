import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {HomePage} from "./pages/home/home.page";
import {BooksListPage} from "./pages/books-list/books-list.page";
import {AuthorsListPage} from "./pages/authors-list/authors-list.page";
import {AuthorPage} from "./pages/author/author.page";
import {BookPage} from "./pages/book/book.page";

const routes = [
  {path: '', component: HomePage},
  {path: 'books', component: BooksListPage},
  {path: 'books/:id', component: BookPage},
  {path: 'authors', component: AuthorsListPage},
  {path: 'authors/:id', component: AuthorPage}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule {
}
