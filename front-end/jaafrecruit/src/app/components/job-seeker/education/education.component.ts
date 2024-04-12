import { Component } from '@angular/core';
import { Education } from 'src/app/models/education';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent {
  educationData: Education[] = [
    { id: 1, degree: 'Bachelor of Science', institution: 'ABC University', graduationYear: new Date('2022-05-31') },
    { id: 2, degree: 'Master of Arts', institution: 'XYZ College', graduationYear: new Date('2023-06-30') }
  ];
  updateEducation(education: Education): void {
    // Implement update logic here
    console.log('Update education:', education);
  }

  deleteEducation(id: number): void {
    // Implement delete logic here
    console.log('Delete education with ID:', id);
    this.educationData = this.educationData.filter(edu => edu.id !== id); // Remove the education item from the array
  }
}
