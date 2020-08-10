import {Component, EventEmitter, Output, OnInit} from '@angular/core';

@Component({
  selector: 'app-date-balance',
  templateUrl: './date-balance.component.html',
  styleUrls: ['./date-balance.component.css']
})
export class DateBalanceComponent implements OnInit {
  dateBalance: any = {
    "date": '',
    "balance": ''
  };
  @Output() myOutput = new EventEmitter<any>();
  maxDate: Date;
  constructor() {
    this.maxDate = new Date();
  }
  output() {
    this.myOutput.emit(this.dateBalance);
  }
  ngOnInit(): void {
  }

}
