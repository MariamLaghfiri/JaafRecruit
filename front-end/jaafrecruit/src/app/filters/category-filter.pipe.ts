import { Pipe, PipeTransform } from '@angular/core';
import { Skills } from 'src/app/models/skills';

@Pipe({
 name: 'categoryFilter'
})
export class CategoryFilterPipe implements PipeTransform {
 transform(skills: Skills[], category: string): Skills[] {
    if (!skills || !category) {
      return skills;
    }
    return skills.filter(skill => skill.category === category);
 }
}
