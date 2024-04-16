import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';

export const loginGuard: CanActivateFn = (route, state) => {
  const auth = inject(AuthService);
  const router = inject(Router);
  if(auth.isLoggedIn()){
    router.navigate(['/jobseeker-dashboard']);
    return false;
  }
  else{
    return true;
  }
};
