import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Education } from 'src/app/models/education';

@Injectable({
  providedIn: 'root'
})
export class EducationService {
  private baseUrl = 'http://localhost:8082/education';

  constructor(private http: HttpClient) { }

  getAllEducations(userId: number): Observable<Education[]> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.get<Education[]>(`${this.baseUrl}/all`, { headers });
  }

  getEducationById(id: number): Observable<Education> {
    return this.http.get<Education>(`${this.baseUrl}/${id}`);
  }

  addEducation(educationDTO: Education, userId: number): Observable<Education> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.post<Education>(`${this.baseUrl}/add`, educationDTO, { headers });
  }

  updateEducation(id: number, educationDTO: Education, userId: number): Observable<Education> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.put<Education>(`${this.baseUrl}/update/${id}`, educationDTO, { headers });
  }

  deleteEducation(id: number): Observable<{ delete: boolean }> {
    return this.http.delete<{ delete: boolean }>(`${this.baseUrl}/delete/${id}`);
  }
}
