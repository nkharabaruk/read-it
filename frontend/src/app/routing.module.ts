import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {BooksComponent} from "./books/books.component";
import {AuthorsComponent} from "./authors/authors.component";


const routes = [
  {path: '', component: HomeComponent},
  {path: 'books', component: BooksComponent},
  {path: 'authors', component: AuthorsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule {
}
