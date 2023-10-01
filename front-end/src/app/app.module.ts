import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UserModuleComponent } from './modules/user-module/user-module.component';
import { AccountModuleComponent } from './modules/account-module/account-module.component';
import { FixDepositAccountModuleComponent } from './modules/fix-deposit-account-module/fix-deposit-account-module.component';
import { SavingsAccountModuleComponent } from './modules/savings-account-module/savings-account-module.component';
import { LoanAccountModuleComponent } from './modules/loan-account-module/loan-account-module.component';
import { TransactionModuleComponent } from './modules/transaction-module/transaction-module.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {RouterLink, RouterOutlet, Routes} from "@angular/router";
import { HomeComponent } from './modules/home/home.component';
import { RouterModule } from '@angular/router';
import { SigninComponent } from './modules/signin/signin.component';
import {HttpClientModule} from "@angular/common/http";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '',
    component: UserModuleComponent
  },
  {
    path: 'account',
    component: AccountModuleComponent
  },
  {
    path: 'fix-deposit-account',
    component: FixDepositAccountModuleComponent
  },
  {
    path: 'savings-account',
    component: SavingsAccountModuleComponent
  },
  {
    path: 'loan-account',
    component: LoanAccountModuleComponent
  },
  {
    path: 'transaction',
    component: TransactionModuleComponent
  },
  {
    path: 'sign-in',
    component: SigninComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    UserModuleComponent,
    AccountModuleComponent,
    FixDepositAccountModuleComponent,
    SavingsAccountModuleComponent,
    LoanAccountModuleComponent,
    TransactionModuleComponent,
    HomeComponent,
    SigninComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    RouterOutlet,
    RouterLink,
    RouterModule.forRoot(routes),
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
