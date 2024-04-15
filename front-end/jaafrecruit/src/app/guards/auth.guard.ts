import { inject } from '@angular/core';
import { CanActivateFn, Router} from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AuthService } from '../service/auth/auth.service';

export const authGuard: CanActivateFn = (route, state) => {

  const auth = inject(AuthService);

  const router = inject(Router);

  const toast = inject(NgToastService);

  if(auth.isLoggedIn()){
    // console.log('token : ', auth.getToken())
    return true;
  }
  else{
    toast.error({detail:"ERROR", summary:"Please Login First!"});
    router.navigate(['login']);
    return false;
  }
}









// export const authGuard: CanActivateFn = (route, state) => {

//   const router = inject(Router);

//   const token = localStorage.getItem('token');

//   console.log('token : ', token)

//   if(token){
//     console.log('true')
//     return true;
//   }else{
//     router.navigate(['login']);
//     console.log('false')
//     return false;
//   }

// };
