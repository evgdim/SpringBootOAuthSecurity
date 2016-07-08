import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import { AuthenticationService } from '../services/authentication.service';
import { MD_CARD_DIRECTIVES } from '@angular2-material/card';
import { MD_BUTTON_DIRECTIVES } from '@angular2-material/button';

@Component({
  moduleId: module.id,
  selector: 'app-auth',
  templateUrl: 'auth.component.html',
  styleUrls: ['auth.component.css'],
  providers: [AuthenticationService],
  directives: [MD_BUTTON_DIRECTIVES]
})
export class AuthComponent implements OnInit {
  username: String;
  password: String;
  constructor(private authService: AuthenticationService) {}

  ngOnInit() {
  }

  getTokenOnClick(){
      this.authService.authenticate(this.username,this.password)
              .subscribe(
                res =>console.log(res.json()),
                err => console.log(err)
              );
  }
}
