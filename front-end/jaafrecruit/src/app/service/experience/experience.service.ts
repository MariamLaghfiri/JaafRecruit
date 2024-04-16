import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Experience } from 'src/app/models/experience';

@Injectable({
 providedIn: 'root'
})
export class ExperienceService {
 private baseUrl = 'http://localhost:8082/experience'; 

 constructor(private http: HttpClient) { }

 getAllExperiences(userId: number): Observable<Experience[]> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.get<Experience[]>(`${this.baseUrl}/all`, { headers});
 }

 getExperienceById(id: number): Observable<Experience> {
    return this.http.get<Experience>(`${this.baseUrl}/${id}`);
 }

 addExperience(experienceDTO: Experience, userId: number): Observable<Experience> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.post<Experience>(`${this.baseUrl}/add`, experienceDTO, { headers});
 }

 updateExperience(id: number, experienceDTO: Experience, userId: number): Observable<Experience> {
    const headers = new HttpHeaders().set('userId', userId.toString());
    return this.http.put<Experience>(`${this.baseUrl}/update/${id}`, experienceDTO, { headers});
 }

 deleteExperience(id: number): Observable<{ [key: string]: boolean }> {
    return this.http.delete<{ [key: string]: boolean }>(`${this.baseUrl}/delete/${id}`);
 }
}
