import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import {HttpService} from '../http.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  confirmPassword = null;
  info: any = {
    "Id": 0,
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
    },
    "loginInfo": {
      "userName": '',
      "password": ''
    }
  };
  error = false;
  saved = false;
  @ViewChild('signUpForm') signUpForm;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private httpService: HttpService) { }
  public signUp(){
    this.error = false;
    this.saved = false;
    console.log(this.info);
    this.httpService.signUp(JSON.stringify(this.info)).subscribe((result) => {
      if (result !== 0) {
        this.saved = true;
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
  public validatePassword(){
    if (this.info.loginInfo.password.length >= 8) {
      if (!this.info.loginInfo.password.match(/^[A-Z][a-zA-Z0-9]*[0-9][a-zA-Z0-9\.]*/)) {
        this.signUpForm.controls.password.setErrors({'requirements': true});
      }
    }
    this.confirmPwd();
  }
  public confirmPwd(){
    if (this.info.loginInfo.password !== this.confirmPassword) {
      this.signUpForm.controls.confirmPassword.setErrors({'matching': true});
    }
    else {
      this.signUpForm.controls.confirmPassword.setErrors(null);
    }
  }
  public goBack() {
    this.router.navigate(['login']);
  }
  ngOnInit(): void {
  }

}
