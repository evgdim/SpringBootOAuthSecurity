import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';

@Injectable()
export class AuthenticationService {

  constructor(private http: Http) {}

  authenticate(username: String, password: String){
    let headers = new Headers();
      headers.append('accept', 'application/json');
      headers.append("authorization","Basic " + btoa("clientapp:123456"));
      headers.append("cache-control", "no-cache");

      let formData = new FormData();
      formData.append("password", password);
      formData.append("username", username);
      formData.append("grant_type", "password");
      formData.append("scope", "read");
      formData.append("client_secret", "123456");
      formData.append("client_id", "clientapp");

      return this.http.post("http://localhost:8080/oauth/token",
                    formData,
                     {headers : headers});
              // .subscribe(
              //   res =>console.log(res.json()),
              //   err => console.log(err)
              // );
  }
}
