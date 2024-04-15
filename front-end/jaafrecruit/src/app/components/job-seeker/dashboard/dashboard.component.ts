import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
 
  constructor(private auth: AuthService){}

  jobData = [
    { id: 1, jobTitle: 'Software Developer', contract: 'Full-Time', jobType: 'Permanent', applicationStatus: 'ACCEPTED' },
    { id: 2, jobTitle: 'Web Designer', contract: 'Part-Time', jobType: 'Contract', applicationStatus: 'PENDING' },
    { id: 3, jobTitle: 'Data Analyst', contract: 'Full-Time', jobType: 'Permanent', applicationStatus: 'REJECTED' }
  ];

  getStatusClass(status: string): string {
    switch (status) {
      case 'ACCEPTED':
        return 'badge bg-success';
      case 'PENDING':
        return 'badge bg-warning';
      case 'REJECTED':
        return 'badge bg-danger';
      default:
        return 'badge bg-secondary'; 
    }
  }

}
