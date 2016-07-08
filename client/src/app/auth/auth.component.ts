import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable }     from 'rxjs/Observable';

@Component({
  moduleId: module.id,
  selector: 'app-auth',
  templateUrl: 'auth.component.html',
  styleUrls: ['auth.component.css']
})
export class AuthComponent implements OnInit {
  token :String;
  constructor(private http: Http) {}

  ngOnInit() {
  }

  getTokenOnClick(){
      let headers = new Headers();
      headers.append('accept', 'application/json');
      headers.append("authorization","Basic " + btoa("clientapp:123456"));
      headers.append("cache-control", "no-cache");

      let formData = new FormData();
      formData.append("password", "pass");
      formData.append("username", "user");
      formData.append("grant_type", "password");
      formData.append("scope", "read");
      formData.append("client_secret", "123456");
      formData.append("client_id", "clientapp");

      this.http.post("http://localhost:8080/oauth/token",
                    formData,
                     {headers : headers})
              .subscribe(
                res =>console.log(res.json()),
                err => console.log(err)
              );
  }
}
