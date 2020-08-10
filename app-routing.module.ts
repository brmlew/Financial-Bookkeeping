import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AccountComponent } from './account/account.component';
import { ResetComponent } from './reset/reset.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SearchComponent } from './search/search.component';
import { PersonalInformationComponent} from './personal-information/personal-information.component';
import { ExpensesComponent} from './expenses/expenses.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'account', component: AccountComponent},
  { path: 'reset', component: ResetComponent},
  { path: 'signUp', component: SignUpComponent},
  { path: 'search', component: SearchComponent},
  { path: 'personalInfo', component: PersonalInformationComponent},
  { path: 'expenses', component: ExpensesComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
