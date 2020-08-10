import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import {HttpService} from '../http.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  showPersonalInformation = false;
  showAccounts = true;
  showAccountHistory = false;
  accountHistory: any;
  selectedAccount: string;
  showExpenses = false;
  maxDate: Date;
  error = false;
  saved = false;
  addingAccount = false;
  addingBalance = false;
  id = null;
  accounts: any;
  info: any = {
    "firstName": '',
    "lastName": '',
    "emailAddress": '',
    "phoneNumber": '',
    "address": {
      "streetNumber": '',
      "streetName": '',
      "city": '',
      "country": '',
      "postalCode": ''
    }
  };
  account: any = {
    "id": this.id,
    "name": '',
    "asOfDate": '',
    "balance": ''
  };
  dateBalance: any = {
    "date": '',
    "balance": ''
  };
  editMode = false;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private httpService: HttpService) {
    this.maxDate = new Date();
  }
  public edit() {
    this.editMode = true;
  }
  public addBalance() {
    this.addingBalance = true;
  }
  public showPersonalInfo() {
    this.showPersonalInformation = true;
    this.showAccounts = false;
    this.showExpenses = false;
    this.showAccountHistory = false;
  }
  public showMyAccounts() {
    this.showAccounts = true;
    this.showPersonalInformation = false;
    this.showExpenses = false;
    this.showAccountHistory = false;
    this.getAccountSummary();
  }
  private getAccountSummary() {
    this.httpService.getAccountSummary(this.id).subscribe((accounts) => {
      console.log(accounts);
      this.accounts = accounts;
    });
  }
  public getAccountHistory(name) {
    this.account = {};
    this.httpService.getAccountHistory(name, this.id).subscribe((accountHistory) => {
      this.selectedAccount = name;
      this.accountHistory = accountHistory;
      this.showAccounts = false;
      this.showAccountHistory = true;
    });
  }
  public saveBalance() {
    this.account.name = this.selectedAccount;
    this.account.id = this.id;
    this.account.asOfDate = this.dateBalance.date;
    this.account.balance = this.dateBalance.balance;
    console.log(this.account);
    this.httpService.saveAccount(JSON.stringify(this.account)).subscribe((result) => {
      if (result) {
        this.saved = true;
        this.addingBalance = false;
        this.getAccountHistory(this.selectedAccount);
      }
      else {
        this.error = true;
      }
    });
  }
  public cancelBalance() {
    this.addingBalance = false;
  }
  public saveAccount() {
    this.account.id = this.id;
    this.account.asOfDate = this.dateBalance.date;
    this.account.balance = this.dateBalance.balance;
    console.log(this.account);
    this.httpService.saveAccount(JSON.stringify(this.account)).subscribe((result) => {
      if (result) {
        this.saved = true;
        this.addingAccount = false;
        this.getAccountSummary();
      }
      else {
        this.error = true;
      }
    });
  }
  public showMyExpenses() {
    this.showAccounts = false;
    this.showPersonalInformation = false;
    this.showExpenses = true;
    this.showAccountHistory = false;
  }
  public cancel() {
    this.addingAccount = false;
  }
  public logout() {
    this.router.navigate(['login']);
  }
  public addAccount() {
    this.addingAccount = true;
  }
  public updated(dateBalance: any) {
    this.dateBalance = dateBalance;
  }
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      console.log(params);
      this.id = params.get('id');
    });
    this.httpService.getInfo(this.id).subscribe((info) => {
      this.info = info;
      console.log(this.info);
    });
    this.getAccountSummary();
  }

}
