import {Component, OnInit, Input, ViewChild} from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import {HttpService} from '../http.service';

@Component({
  selector: 'app-expenses',
  templateUrl: './expenses.component.html',
  styleUrls: ['./expenses.component.css']
})
export class ExpensesComponent implements OnInit{
  expenses: any;
  addingExpense = false;
  saved = false;
  error = false;
  @Input() id: any;
  expense: any = {
    "id": this.id,
    "transactionDate": '',
    "transactionAmount": '',
    "transactionType": '',
    "notes": ''
  };
  dateBalance: {
    "date": any,
    "balance": any
  };
  maxDate: Date;
  @ViewChild('expenseForm') expenseForm;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private httpService: HttpService) {
    this.maxDate = new Date();
  }
  public getExpenses() {
    this.httpService.getExpenses(this.id).subscribe((expenses) => {
      this.expenses = expenses;
      console.log(this.expenses);
    });
  }
  public addExpense() {
    this.addingExpense = true;
    this.saved = false;
    this.error = false;
  }
  public saveExpense() {
    this.expense.id = this.id;
    this.expense.transactionDate = this.dateBalance.date;
    this.expense.transactionAmount = this.dateBalance.balance;
    console.log(this.dateBalance);
    this.httpService.saveExpense(JSON.stringify(this.expense)).subscribe((result) => {
      if (result !== 0) {
        this.saved = true;
        this.addingExpense = false;
        this.getExpenses();
        this.expense = {};
      }
      else {
        this.error = true;
      }
    },
    (err => {
      console.log(err);
      this.error = true;
    }));
  }
  public cancel() {
    this.addingExpense = false;
  }
  public updated(dateBalance: any) {
    this.dateBalance = dateBalance;
  }
  ngOnInit(): void {
    this.getExpenses();
  }
}
