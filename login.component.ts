import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import {HttpService} from '../http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userName = null;
  password = null;
  id = null;
  error = false;
  @ViewChild('loginForm') loginForm;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private httpService: HttpService) { }
  public login(){
    this.httpService.login(this.userName, this.password).subscribe((id) => {
      this.id = id;
      if (this.id === 0) {
        this.error = true;
      }
      else {
        this.router.navigate(['account'], {queryParams: {id: this.id}});
      }
    });
  }
  public validatePassword(){
    if (this.password.length >= 8) {
      if (!this.password.match(/^[A-Z][a-zA-Z0-9]*[0-9][a-zA-Z0-9\.]*/)) {
        this.loginForm.controls.password.setErrors({'requirements': true});
      }
    }
  }
  public resetPassword(){
    this.router.navigate(['reset']);
  }
  public signUp(){
    this.router.navigate(['signUp']);
  }
  public search(){
    this.router.navigate(['search']);
  }
  ngOnInit(): void {
  }

}
