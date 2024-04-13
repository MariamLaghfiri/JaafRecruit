import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  type: string = "password";
  isText: boolean = false;
  eyeIcon: string = "bi bi-eye-slash";

  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private auth: AuthService) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName:['',Validators.required],
      lastName:['',Validators.required],
      username:['',Validators.required],
      address:['',Validators.required],
      phoneNumber:['',Validators.required],
      email: ['',[Validators.required, Validators.email]],
      role: ['',Validators.required],
      password: ['',[Validators.required, Validators.minLength(6)]]
    })
  }
  hideShowPassword() {
    this.isText = !this.isText;
    this.isText ? this.eyeIcon = "bi bi-eye" : this.eyeIcon = "bi bi-eye-slash";
    this.isText ? this.type = "text" : this.type = "password";
  }

  onSubmit(){
    if(this.registerForm.valid){
      console.log(this.registerForm.value);
      //sende to DB
      //sende to DB
      this.auth.register(this.registerForm.value)
      .subscribe({
        next:(res)=>{
          alert(res.message)
        },
        error:(err)=>{
          alert(err?.error.message);
        }
      })
    }
    else{
      console.log("form is not valid");
      //throw error
    }
  }
}
