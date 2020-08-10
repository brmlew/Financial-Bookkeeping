import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import {HttpService} from '../http.service';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css']
})
export class ResetComponent implements OnInit {
  userName = null;
  oldPassword = null;
  newPassword = null;
  confirmPassword = null;
  resetResult = null;
  saveResult = null;
  success = false;
  error = false;
  @ViewChild('resetForm') resetForm;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private httpService: HttpService) { }
  public validatePassword(){
    if (this.newPassword.length >= 8) {
      if (!this.newPassword.match(/^[A-Z][a-zA-Z0-9]*[0-9][a-zA-Z0-9\.]*/)) {
        this.resetForm.controls.newPassword.setErrors({'requirements': true});
      }
    }
    this.confirmPwd();
  }
  public confirmPwd(){
    if (this.newPassword !== this.confirmPassword) {
      this.resetForm.controls.confirmPassword.setErrors({'matching': true});
    }
    else {
      this.resetForm.controls.confirmPassword.setErrors(null);
    }
  }
  public reset() {
    this.success = false;
    this.error = false;
    this.httpService.reset(this.userName, this.oldPassword).subscribe((resetResult) => {
      this.resetResult = resetResult !== 0;
      if (resetResult) {
        this.httpService.save(this.userName, this.newPassword).subscribe((saveResult) => {
          this.saveResult = saveResult !== 0;
          if (saveResult) {
            this.success = true;
          }
          else {
            this.error = true;
          }
        });
      } else {
        this.error = true;
      }
    });
  }
  public goBack() {
    this.router.navigate(['login']);
  }
  ngOnInit(): void {
  }

}
