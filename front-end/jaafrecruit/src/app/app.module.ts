import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/job-seeker/profile/profile.component';
import { DashboardComponent } from './components/job-seeker/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ContactAdminComponent } from './components/contact-admin/contact-admin.component';
import { JobPostingComponent } from './components/job-posting/job-posting.component';
import { RecruiterDashboardComponent } from './components/recruter/recruiter-dashboard/recruiter-dashboard.component';
import { SkillsComponent } from './components/job-seeker/skills/skills.component';
import { EducationComponent } from './components/job-seeker/education/education.component';
import { ExperienceComponent } from './components/job-seeker/experience/experience.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from"@angular/common/http"
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    DashboardComponent,
    LoginComponent,
    RegisterComponent,
    ContactAdminComponent,
    JobPostingComponent,
    RecruiterDashboardComponent,
    SkillsComponent,
    EducationComponent,
    ExperienceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
