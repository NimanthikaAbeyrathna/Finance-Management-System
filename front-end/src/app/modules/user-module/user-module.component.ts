import {Component, inject} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, Observable, shareReplay} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";
import {User} from "../../models/user";
import {UserFinder} from "../../models/userfinder";

@Component({
  selector: 'app-user-module',
  templateUrl: './user-module.component.html',
  styleUrls: ['./user-module.component.scss']
})
export class UserModuleComponent {
  private breakpointObserver = inject(BreakpointObserver);
  username:string = '';
  password: string = '';
  user: {} = {};


  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay());

  constructor(private http: HttpClient) {

  }

  onFormSubmit(event: any, displayBtn: HTMLButtonElement) {

    event.preventDefault();

    const userFinder = {
      username: this.username,
      password: this.password
    };

    this.http.post<UserFinder>(`${environment.apiUrl}/user/userFinder`, userFinder)
      .subscribe((response: any) => {
          // Success handling here
          this.user = response;
          console.log(this.user);
          displayBtn.click();

        },
        (error) => {
          alert('please sign in');
        });
  }
}
