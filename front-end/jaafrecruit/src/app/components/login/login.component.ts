import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  type: string = "password";
  isText: boolean = false;
  eyeIcon: string = "bi bi-eye-slash";

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private auth: AuthService,private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['',Validators.required],
      password: ['',[Validators.required, Validators.minLength(6)]]
    })
  }

  hideShowPassword() {
    this.isText = !this.isText;
    this.isText ? this.eyeIcon = "bi bi-eye" : this.eyeIcon = "bi bi-eye-slash";
    this.isText ? this.type = "text" : this.type = "password";
  }

  onLogin(){
    if(this.loginForm.valid){
      console.log(this.loginForm.value);
      //sende to DB
      this.auth.login(this.loginForm.value)
      .subscribe({
        next:(res)=>{
          console.log("success");
          this.auth.storToken(res.access_token);
          this.router.navigate(['jobseeker-dashboard']);
        },
        error:(err)=>{
          console.log("error");
        }
      })
    }
    else{
      console.log("form is not valid");
      //throw error
    }
  }

  private validateAllFormFields(formGroup:FormGroup){
Object.keys(formGroup.controls)
  }
}
