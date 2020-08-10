import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpClient: HttpClient) { }
  public login(username, password) {
    return this.httpClient.get('http://localhost:8081/Website/login?username=' + username + '&password=' + password);
  }

  public getInfo(id) {
    return this.httpClient.get('http://localhost:8081/Website/personalInfo?id=' + id);
  }

  public saveInfo(info) {
    console.log(info);
    return this.httpClient.post('http://localhost:8081/Website/personalInfo', info);
  }

  public reset(username, oldPassword) {
    return this.httpClient.get('http://localhost:8081/Website/login?username=' + username + '&password=' + oldPassword);
  }
  public save(username, newPassword) {
    return this.httpClient.post('http://localhost:8081/Website/login?username=' + username + '&password=' + newPassword, null);
  }

  public search(firstName, lastName) {
    return this.httpClient.get('http://localhost:8081/Website/search?firstName=' + firstName + '&lastName=' + lastName);
  }
  public signUp(info) {
    return this.httpClient.post('http://localhost:8081/Website/personalInfo', info);
  }
  public getAccountSummary(id) {
    return this.httpClient.get('http://localhost:8081/Website/accounts?id=' + id);
  }
  public getAccountHistory(name, id) {
    return this.httpClient.get('http://localhost:8081/Website/accounts?id=' + id + '&name=' + name);
  }
  public saveAccount(account) {
    return this.httpClient.post('http://localhost:8081/Website/accounts', account);
  }
  public getExpenses(id) {
    return this.httpClient.get('http://localhost:8081/Website/expenses?id=' + id);
  }
  public saveExpense(expense) {
    return this.httpClient.post('http://localhost:8081/Website/expenses', expense);
  }
}
