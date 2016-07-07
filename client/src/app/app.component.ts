import { Component } from '@angular/core';
import { AuthComponent } from './auth';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  directives: [AuthComponent]
})
export class AppComponent {
  title = 'app works!';
}
