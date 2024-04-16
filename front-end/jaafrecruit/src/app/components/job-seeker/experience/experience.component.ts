import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Experience } from 'src/app/models/experience'; // Assuming you have an Experience model
import { ExperienceService } from 'src/app/service/experience/experience.service';
import { AuthService } from 'src/app/service/auth/auth.service';

declare var bootstrap: any;

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {
  experienceData: Experience[] = [];
  userId: number = 1;
  index!: number;
  addExperienceForm!: FormGroup;
  updateExperienceForm!: FormGroup;
  constructor(private experienceService: ExperienceService, private auth: AuthService) { }

  ngOnInit(): void {
    this.fetchExperienceData();
    this.initForm();
    this.initUpdateForm();
  }

  initForm(): void {
    this.addExperienceForm = new FormGroup({
      companyName: new FormControl('', Validators.required),
      jobTitle: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required)
    });
  }

  closeAddModal() {
    var modalElement = document.getElementById('addExperienceModal');
    if (modalElement) {
      var modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.hide();
      modalElement.setAttribute('aria-hidden', 'true');
    } else {
      console.error('Modal element not found');
    }
  }

  fetchExperienceData(): void {
    this.experienceService.getAllExperiences(this.userId).subscribe(data => {
      this.experienceData = data;
    });
  }

  openAddExperienceModal(): void {
    const modalElement = document.getElementById('addExperienceModal');
    if (modalElement) {
      const modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.show();
    } else {
      console.error('Modal element not found');
    }
  }

  addExperience(formValues: any): void {
    const newExperience: Experience = {
      id: 0,
      companyName: formValues.companyName,
      jobTitle: formValues.jobTitle,
      description: formValues.description,
      startDate: new Date(formValues.startDate),
      endDate: new Date(formValues.endDate)
    };

    this.experienceService.addExperience(newExperience, this.userId).subscribe(response => {
      this.experienceData.push(response);
      this.closeAddModal();
    });
  }

  initUpdateForm(): void {
    this.updateExperienceForm = new FormGroup({
      id: new FormControl(''), // Hidden ID field
      companyName: new FormControl('', Validators.required),
      jobTitle: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required)
    });
  }

  openUpdateExperienceModal(experience: Experience): void {
    const index = this.experienceData.findIndex(e => e.id === experience.id);
    if (index !== -1) {
       this.index = index;
       this.updateExperienceForm.setValue({
         id: experience.id,
         companyName: experience.companyName,
         jobTitle: experience.jobTitle,
         description: experience.description,
         startDate: experience.startDate,
         endDate: experience.endDate
       });
       // Open the modal
       const modalElement = document.getElementById('updateExperienceModal');
       if (modalElement) {
         const modalInstance = new bootstrap.Modal(modalElement);
         modalInstance.show();
       } else {
         console.error('Modal element not found');
       }
    }
   }
   
  openUpdateModal(experience: Experience): void {
    this.updateExperienceForm.setValue({
      id: experience.id,
      companyName: experience.companyName,
      jobTitle: experience.jobTitle,
      description: experience.description,
      startDate: experience.startDate,
      endDate: experience.endDate
    });
    // Open the modal
    const modalElement = document.getElementById('updateExperienceModal');
    const modalInstance = new bootstrap.Modal(modalElement);
    modalInstance.show();
  }

  updateExperience(formValues: any): void {
    const updatedExperience: Experience = {
      id: formValues.id,
      companyName: formValues.companyName,
      jobTitle: formValues.jobTitle,
      description: formValues.description,
      startDate: new Date(formValues.startDate),
      endDate: new Date(formValues.endDate)
    };

    this.experienceService.updateExperience(updatedExperience.id, updatedExperience, this.userId).subscribe(response => {
      const index = this.experienceData.findIndex(e => e.id === updatedExperience.id);
      if (index !== -1) {
        this.experienceData[index] = updatedExperience;
      }
      // Close the modal
      const modalElement = document.getElementById('updateExperienceModal');
      const modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.hide();
    });
  }

  deleteExperience(id: number): void {
    this.experienceService.deleteExperience(id).subscribe(response => {
      this.experienceData = this.experienceData.filter(e => e.id !== id);
    });
  }
}
