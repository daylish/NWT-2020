import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';
// import { FormBuilder, FormGroup } from "@angular/forms";
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-user-display',
  templateUrl: './user-display.component.html',
  styleUrls: ['./user-display.component.css']
})
export class UserDisplayComponent implements OnInit {

  userForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.compose(
        [Validators.required, Validators.minLength(6), Validators.maxLength(50)])),
    email: new FormControl('', Validators.compose(
        [Validators.required, Validators.email])),
    location: new FormControl('', Validators.required),
  });

  // just for eventual testing purposes
  /*
  user: User = {
    userID: 1,
    username: 'testusername',
    password: 'testpassword',
    email: 'testemail@gmail.com',
    location: 'USA'
  };
  */

  users: User[];

  form: FormGroup;

  constructor(private userService: UserService, private http: HttpClient) {
  }
  /*
  constructor(private userService: UserService, private http: HttpClient, public fb: FormBuilder) {
    this.form = this.fb.group({
      username: 'testusername',
      password: 'testpassword',
      email: 'testemail@test.com',
      location: 'Sweden'
  })}
  */

  ngOnInit() {
    this.getUsers();
  }

  onDeleteClicked(item: User) {
    console.warn("Clicked on user with id " + item.userId + " and username " + item.username);
    this.userService.deleteUser(item.userId).subscribe();
  }

  getUsers(): void {
    this.userService.getUsers()
      .subscribe(users => this.users = users);
  }

  // on user form submit
  onSubmit() {
    this.userService.postUser(this.userForm.value)
      .subscribe(user => console.warn("Added user: " + user));
    console.warn(this.userForm.value);
  }

  /*
  submitForm() {
    var userFormData: any = new FormData();
    userFormData.append("username", this.form.get('username').value);
    userFormData.append("password", this.form.get('password').value);
    userFormData.append("email", this.form.get('email').value);
    userFormData.append("location", this.form.get('location').value);
    console.log(userFormData);

    this.http.post('http://localhost:8081/users/new', {
		"username": "cschule1",
	    "password" : "testPassword2",
	    "email" : "testing@nba.com",
	    "location" : "France"
    }).subscribe(
      (response) => console.log(response),
      (error) => console.log(error)
    )
  }
  */
}
