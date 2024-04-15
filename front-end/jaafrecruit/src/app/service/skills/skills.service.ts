import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Skills } from 'src/app/models/skills';

@Injectable({
  providedIn: 'root'
})
export class SkillsService {
  private apiUrl = 'http://localhost:4441/skills'; 

  constructor(private http: HttpClient) { }

  showAllSkills(userId: number): Observable<Skills[]> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.get<Skills[]>(`${this.apiUrl}/all`, { headers });
  }

  findSkillsByCategory(category: string, userId: number): Observable<Skills[]> {
    const headers = new HttpHeaders().set('userId', userId.toString());
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
