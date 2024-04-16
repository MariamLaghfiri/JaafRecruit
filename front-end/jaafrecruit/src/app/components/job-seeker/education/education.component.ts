import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Education } from 'src/app/models/education';
import { AuthService } from 'src/app/service/auth/auth.service';
import { EducationService } from 'src/app/service/education/education.service';

declare var bootstrap: any;

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})


export class EducationComponent implements OnInit {

  constructor(private auth: AuthService, private educationService: EducationService) { }

  educations: Education[] = [];
  userId: number = 1;
  addEducationForm!: FormGroup;
  updateEducationForm!: FormGroup;
  index!: number;
  submitted = false;

  ngOnInit(): void {
    this.fetchEducationData();
    this.initForm();
    this.initUpdateForm();
  }

  initForm(): void {
    this.addEducationForm = new FormGroup({
      degree: new FormControl('', Validators.required),
      institution: new FormControl('', Validators.required),
      graduationYear: new FormControl('', Validators.required)
    });
  }

  fetchEducationData(): void {
    this.educationService.getAllEducations(this.userId).subscribe(data => {
      this.educations = data;
    });
  }

  openAddEducationModal(): void {
    const modalElement = document.getElementById('addEducationModal');
    const modalInstance = new bootstrap.Modal(modalElement);
    modalInstance.show();
  }

  closeAddModal() {
    var modalElement = document.getElementById('addEducationModal');
    if (modalElement) {
      var modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.hide();
      modalElement.setAttribute('aria-hidden', 'true');
    } else {
      console.error('Modal element not found');
    }
  }

  addEducation(formValues: any): void {
    const newEducation: Education = {
      id: 0, // Assuming the backend will generate the ID
      degree: formValues.degree,
      institution: formValues.institution,
      graduationYear: new Date(formValues.graduationYear)
    };

    this.educationService.addEducation(newEducation, this.userId).subscribe(response => {
      // Handle the response, e.g., add the new education to the list
      this.educations.push(response);
      // Close the modal
      const modalElement = document.getElementById('addEducationModal');
      const modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.hide();
    });
  }

  openUpdateEducationModal(education: Education): void {
    const index = this.educations.findIndex(e => e.id === education.id);
    if (index !== -1) {
      this.index = index;
      this.updateEducationForm.setValue({
        id: education.id,
        degree: education.degree,
        institution: education.institution,
        graduationYear: education.graduationYear
      });
      // Open the modal
      const modalElement = document.getElementById('updateEducationModal');
      const modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.show();
    }
  }
  
  initUpdateForm(): void {
    this.updateEducationForm = new FormGroup({
      id: new FormControl(''),
      degree: new FormControl('', Validators.required),
      institution: new FormControl('', Validators.required),
      graduationYear: new FormControl('', Validators.required)
    });
  }

  updateEducation(formValues: any): void {
    const updatedEducation: Education = {
      id: formValues.id,
      degree: formValues.degree,
      institution: formValues.institution,
      graduationYear: new Date(formValues.graduationYear)
    };

    // Update the education in the educationData array
    const index = this.educations.findIndex(e => e.id === updatedEducation.id);
    if (index !== -1) {
      this.educations[index] = updatedEducation;
    }

    // Update the education in the database
    this.educationService.updateEducation(updatedEducation.id, updatedEducation, this.userId).subscribe(response => {
      console.log('Education updated successfully');
    }, error => {
      console.error('Error updating education:', error);
    });

    // Close the modal
    const modalElement = document.getElementById('updateEducationModal');
    const modalInstance = new bootstrap.Modal(modalElement);
    modalInstance.hide();
  }


  deleteEducation(id: number): void {
    this.educationService.deleteEducation(id).subscribe(response => {
      console.log('Education deleted successfully');
      this.educations = this.educations.filter(e => e.id !== id);
    }, error => {
      console.error('Error deleting education:', error);
    });
  }


}
