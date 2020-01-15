import { Injectable } from '@angular/core';
import { AccountService } from '../auth/account.service';

@Injectable({
  providedIn: 'root'
})
export class UserRoleService {
  private _userRole: string = ''
  private _isEmployer: boolean = false;

  constructor(
    accountService: AccountService
  ) {
    accountService.get().subscribe(res => {
      let user = res.body;
      if (user && user.role) {
        this._userRole = user.role.toLowerCase();
        this.setUserRole(this._userRole);
      }
    })
  }
  public setUserRole(roleName: string) {
    this._isEmployer = roleName === 'employer';
    this._userRole = this._isEmployer ? roleName : 'employee'
  }

  public get userRole(): string {
    return this._userRole;
  }

  public get isEmployer(): boolean {
    return this._isEmployer;
  }
}
