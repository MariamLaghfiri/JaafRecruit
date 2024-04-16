import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Skills } from 'src/app/models/skills';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SkillsService {
  private apiUrl = 'http://localhost:8082/skills'; 

  constructor(private http: HttpClient,private auth:AuthService) { }

  showAllSkills(userId: number): Observable<Skills[]> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.get<Skills[]>(`${this.apiUrl}/all`, { headers });
  }

  findSkillsByCategory(category: string, userId: number): Observable<Skills[]> {
    const userIdstr = this.auth.getUserId();
    if (!userIdstr) {
      console.error('User ID not found in JWT');
      return new Observable(observer => {
        observer.error('User ID not found in JWT');
        observer.complete();
      });
    }
    const headers = new HttpHeaders().set('userId', userIdstr);
    return this.http.get<Skills[]>(`${this.apiUrl}/get-by-category/${category}`, { headers });
  }

  getSkillsById(id: number): Observable<Skills> {
    return this.http.get<Skills>(`${this.apiUrl}/${id}`);
  }

  addSkills(skillsDTO: Skills, userId: number): Observable<Skills> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.post<Skills>(`${this.apiUrl}/add`, skillsDTO, { headers });
  }

  updateSkills(id: number, skillsDTO: Skills, userId: number): Observable<Skills> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.put<Skills>(`${this.apiUrl}/update/${id}`, skillsDTO, { headers });
  }

  deleteSkills(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${id}`);
  }
}
