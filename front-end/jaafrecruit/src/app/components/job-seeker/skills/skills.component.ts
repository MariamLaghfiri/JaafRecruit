import { Component } from '@angular/core';
import { Skills } from 'src/app/models/skills';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent {
  skillsData: Skills[] = [
    { id: 1, name: 'JavaScript', category: 'TECHNICAL', level: 'Intermediate' },
    { id: 2, name: 'HTML', category: 'TECHNICAL', level: 'Beginner' },
    { id: 3, name: 'CSS', category: 'TECHNICAL', level: 'Beginner' },
    { id: 3, name: 'comunication', category: 'SOFT_SKILLS', level: 'Beginner' }
    // Add more skills as needed
  ];

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

  updateSkill(skill: Skills): void {
    // Implement update logic here
    console.log('Update skill:', skill);
  }

  deleteSkill(id: number): void {
    // Implement delete logic here
    console.log('Delete skill with ID:', id);
    this.skillsData = this.skillsData.filter(sk => sk.id !== id); // Remove the skill item from the array
  }
}
