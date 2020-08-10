import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import {HttpService} from '../http.service';

@Component({
  selector: 'app-personalInfo',
  templateUrl: './personal-information.component.html',
  styleUrls: ['./personal-information.component.css']
})
export class PersonalInformationComponent {
  result = false;
  saved = false;
  id = null;
  @Input() info: any;
  editMode = false;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private httpService: HttpService) { }
  public edit() {
    this.editMode = true;
  }
  public cancel() {
    this.editMode = false;
  }
  public logout() {
    this.router.navigate(['login']);
  }
  public save() {
    this.httpService.saveInfo(JSON.stringify(this.info)).subscribe((result) => {
        console.log("test");
        this.result = result !== 0;
        this.saved = true;
        if (result) {
          this.editMode = false;
        }
      },
      (error => {
        console.log(error);
        this.saved = true;
        this.result = false;
      }));
  }
}

