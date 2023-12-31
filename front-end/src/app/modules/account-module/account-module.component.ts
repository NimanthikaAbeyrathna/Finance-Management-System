import {Component, inject} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, Observable, shareReplay} from "rxjs";
import {HttpClient} from "@angular/common/http";
import Big from "big.js";
import {User} from "../../models/user";
import {environment} from "../../../environments/environment.development";
import {Account} from "../../models/account";

@Component({
  selector: 'app-account-module',
  templateUrl: './account-module.component.html',
  styleUrls: ['./account-module.component.scss']
})
export class AccountModuleComponent {

  private breakpointObserver = inject(BreakpointObserver);

    accountType: string = '' ;
    initialDeposit: Big = new Big( 0.00);
    userId: number = 0;

    accountNumber: number = 0;
    balance: Big = new Big( 0.00);


  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay());

  constructor(private http: HttpClient) {
  }

  onFormSubmit(event: any) {
    event.preventDefault();

    const account = {
        accountType:this.accountType,
        balance:this.initialDeposit,
        userId:this.userId
    };

      this.http.post<Account>(`${environment.apiUrl}/account`, account)
          .subscribe((response: any) => {
                  // Success handling here
                  alert(`Successfully created, and kindly remember this one-time autogenerated Account ID for future interactions.your account id is :${response}`);

              },
              (error) => {
                  console.log(error);
              });
  }

    checkBalance(event: any) {
      event.preventDefault();

        this.http.get(`${environment.apiUrl}/account/${this.accountNumber}`)
            .subscribe((response: any) => {
                    if (response === null){
                        alert('please insert correct account number');
                    }
                    this.balance = response;
                },
                (error) => {
                    console.log(error);
                });

    }
}
