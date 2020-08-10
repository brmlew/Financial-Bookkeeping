import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import {HttpService} from '../http.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  results = null;
  firstName = '';
  lastName = '';
  resultsFound = false;
  searched = false;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private httpService: HttpService) { }
  public search() {
    this.results = null;
    this.searched = false;
    this.httpService.search(this.firstName, this.lastName).subscribe((results) => {
      console.log(results);
      this.results = results;
      this.resultsFound = this.results.length > 0;
      this.searched = true;
    });
  }
  public goBack() {
    this.router.navigate(['login']);
  }
  ngOnInit(): void {
  }

}
