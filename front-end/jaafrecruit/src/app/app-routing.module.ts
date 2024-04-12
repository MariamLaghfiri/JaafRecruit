import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/job-seeker/dashboard/dashboard.component';
import { ProfileComponent } from './components/job-seeker/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ContactAdminComponent } from './components/contact-admin/contact-admin.component';
import { JobPostingComponent } from './components/job-posting/job-posting.component';
import { RecruiterDashboardComponent } from './components/recruter/recruiter-dashboard/recruiter-dashboard.component';
import { EducationComponent } from './components/job-seeker/education/education.component';
import { SkillsComponent } from './components/job-seeker/skills/skills.component';
import { ExperienceComponent } from './components/job-seeker/experience/experience.component';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "jobseeker-dashboard",
    component: DashboardComponent
  },
  {
    path: "jobseeker-profile",
    component: ProfileComponent
  },
  {
    path: "jobseeker-skills",
    component: SkillsComponent
  },
  {
    path: "jobseeker-education",
    component: EducationComponent
  },
  {
    path: "jobseeker-experiance",
    component: ExperienceComponent
  },
  {
    path: "contact-admin",
    component: ContactAdminComponent
  },
  {
    path: "job-posting",
    component: JobPostingComponent
  },
  {
    path: "recruter-dashboard",
    component: RecruiterDashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
