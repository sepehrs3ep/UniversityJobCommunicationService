import { Injectable, isDevMode } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthServerProvider } from './auth-jwt.service';
import { Principal } from './principal.service';
import { StateStorageService } from './state-storage.service';


@Injectable({ providedIn: 'root' })
export class UserRouteAccessService implements CanActivate {
  constructor(
    private router: Router,
    private principal: Principal,
    private stateStorageService: StateStorageService,
    private authService: AuthServerProvider
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Promise<boolean> {
    return this.simpleCanActive();
    // const authorities = route.data['authorities'];
    // return this.checkLogin(authorities, state.url);
  }

  checkLogin(authorities: string[], url: string): Promise<boolean> {
    const principal = this.principal;
    return Promise.resolve(
      principal.identity().then(account => {
        if (account) {
          if (!authorities || authorities.length === 0) {
            return true;
          }
          return principal.hasAnyAuthority(authorities).then(response => {
            if (response) {
              return true;
            }
            if (isDevMode()) {
              console.error(
                'User has not any of required authorities: ',
                authorities
              );
            }
            this.stateStorageService.storeUrl(url);
            this.router.navigate(['accessdenied']).then(() => {
              // only show the login dialog, if the user hasn't logged in yet
              if (!account) {
                this.router.navigate(['/home']);
              }
            });
            return false;
          });
        } else {
          this.router.navigate(['account/login']);
          return true;
        }
      })
    );
  }

  simpleCanActive(): boolean {
    if (this.authService.getToken() !== null && this.authService.getToken() !== undefined)
      return true;
    this.router.navigate(['account', 'login']);
    return false;
  }
}
