import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Skills } from 'src/app/models/skills';
import { AuthService } from 'src/app/service/auth/auth.service';
import { SkillsService } from 'src/app/service/skills/skills.service';

declare var bootstrap: any;

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {
  skillsData: Skills[] = [];
  selectedCategory: string = '';
  userIdtest: string = this.auth.getUserId() as string;
  userId: number = 1;
  addSkillForm!: FormGroup;
  index!: number;
  updateSkillForm!: FormGroup;
  constructor(private skillsService: SkillsService, private auth: AuthService) { }

  ngOnInit(): void {
    console.log("hello");
    console.log("user id : : ",this.auth.getUserId());
    this.fetchSkillsData();
    this.initForm();
    this.initUpdateForm()
  }

  initForm(): void {
    this.addSkillForm = new FormGroup({
      name: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
      level: new FormControl('', Validators.required)
    });
  }

  closeAddModal() {
    var modalElement = document.getElementById('addSkillModal');
    if (modalElement) {
      var modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.hide();
      modalElement.setAttribute('aria-hidden', 'true');
    } else {
      console.error('Modal element not found');
    }
  }


  fetchSkillsData(): void {
    this.skillsService.showAllSkills(this.userId).subscribe(data => {
      this.skillsData = data;
      console.log('userId : ',this.userIdtest);
    });
  }

  getCategoryClass(category: string): string {
    switch (category) {
      case 'TECHNICAL':
        return 'badge bg-success';
      case 'SOFT_SKILLS':
        return 'badge bg-warning';
      default:
        return 'badge bg-secondary';
    }
  }

  openAddSkillModal(): void {
    const modalElement = document.getElementById('addSkillModal');
    const modalInstance = new bootstrap.Modal(modalElement);
    modalInstance.show();
  }

  addSkill(formValues: any): void {
    const newSkill: Skills = {
      id: 0,
      name: formValues.name,
      category: formValues.category,
      level: formValues.level
      // Add any other properties as needed
    };

    this.skillsService.addSkills(newSkill, this.userId).subscribe(response => {
      // Handle the response, e.g., add the new skill to the list
      this.skillsData.push(response);
      // Close the modal
      const modalElement = document.getElementById('addSkillModal');
      const modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.hide();
    });
  }

  initUpdateForm(): void {
    this.updateSkillForm = new FormGroup({
      id: new FormControl(''), // Hidden ID field
      name: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
      level: new FormControl('', Validators.required)
    });
  }

  openUpdateModal(skill: Skills): void {
    const index = this.skillsData.findIndex(s => s.id === skill.id);
    if (index !== -1) {
      this.index = index;
      this.updateSkillForm.setValue({
        id: skill.id,
        name: skill.name,
        category: skill.category,
        level: skill.level
      });
      // Open the modal
      const modalElement = document.getElementById('updateSkillModal');
      const modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.show();
    }
  }

  updateSkill(formValues: any): void {
    const updatedSkill: Skills = {
      id: this.skillsData[this.index].id,
      name: formValues.name,
      category: formValues.category,
      level: formValues.level
    };

    // Update the skill in the skillsData array
    this.skillsData[this.index] = updatedSkill;

    // Update the skill in the database
    this.skillsService.updateSkills(updatedSkill.id, updatedSkill, this.userId).subscribe(response => {
      console.log('Skill updated successfully');
    }, error => {
      console.error('Error updating skill:', error);
    });

    // Close the modal
    const modalElement = document.getElementById('updateSkillModal');
    const modalInstance = new bootstrap.Modal(modalElement);
    modalInstance.hide();
  }


  deleteSkill(id: number): void {
    this.skillsService.deleteSkills(id).subscribe(response => {
      console.log('Skill deleted successfully');
      this.skillsData = this.skillsData.filter(sk => sk.id !== id);
    }, error => {
      console.error('Error deleting skill:', error);
    });
  }
}
