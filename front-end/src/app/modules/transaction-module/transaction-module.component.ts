import {Component, inject} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, Observable, shareReplay} from "rxjs";
import {Transaction} from "../../models/transaction";
import {environment} from "../../../environments/environment.development";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-transaction-module',
  templateUrl: './transaction-module.component.html',
  styleUrls: ['./transaction-module.component.scss']
})
export class TransactionModuleComponent {
  private breakpointObserver = inject(BreakpointObserver);
  accountNumber: any;
  transactionType: any;
  amount: any;


  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay());


  constructor(private http: HttpClient) {

  }

  onFormSubmit(event: any) {
    event.preventDefault();

    const javascriptDate = new Date();

    const transaction = {
      transactionType:this.transactionType,
      amount: this.amount,
      timestamp :javascriptDate.toISOString(),
      accountId :this.accountNumber
    };

    console.log(transaction);

    this.http.post<Transaction>(`${environment.apiUrl}/transaction`, transaction)
      .subscribe((response: any) => {

          alert(`Successfull`);

        },
        (error) => {
          console.log(error);
          alert(`Error: ${error.message || 'An error occurred'}`);
        });
  }
}
