import { Component } from '@angular/core';
import { Experience } from 'src/app/models/experience';
import * as $ from 'jquery';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent {

  selectedExperience: Experience | null = null;

  constructor() { }

  // Method to open the update experience modal
  openUpdateModal(experience: Experience): void {
    this.selectedExperience = experience;
    ($('#updateExperienceModal') as any).modal('show');
  }

  // Method to close the update experience modal
  closeUpdateModal(): void {
    this.selectedExperience = null;
    ($('#updateExperienceModal') as any).modal('hide');
  }

  // Method to update the experience details
  updateExperience(): void {
    if (this.selectedExperience) {
      // Implement update logic here
      console.log('Updating experience:', this.selectedExperience);
      this.closeUpdateModal(); // Close the modal after updating
    }
  }


  experienceData: Experience[] = [
    {
      id: 1,
      companyName: 'ABC Company',
      jobTitle: 'Software Engineer',
      description: 'Developed web applications using Angular and Node.js',
      startDate: new Date('2022-01-01'),
      endDate: new Date('2023-06-30')
    },
    {
      id: 2,
      companyName: 'XYZ Corporation',
      jobTitle: 'Frontend Developer',
      description: 'Worked on UI design and implemented responsive layouts',
      startDate: new Date('2020-05-15'),
      endDate: new Date('2021-12-31')
    }
    // Add more experiences as needed
  ];

  deleteExperience(id: number): void {
    // Implement delete logic here
    console.log('Delete experience with ID:', id);
    this.experienceData = this.experienceData.filter(exp => exp.id !== id); // Remove the experience item from the array
  }
}
