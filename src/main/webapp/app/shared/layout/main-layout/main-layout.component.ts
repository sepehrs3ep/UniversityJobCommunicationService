import { Component } from '@angular/core';
import { AccountService } from '@app/core/auth/account.service';
import { UserRoleService } from '@app/core/service/user-role.service';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss']
})
export class MainLayoutComponent {
  opened: boolean = false;

  isEmployer: boolean = false;
  constructor(
    private userRoleService: UserRoleService,
    private accountService: AccountService
  ) { }
  ngOnInit() {
    let role = this.userRoleService.userRole;
    if (role !== 'employee' && role !== 'employer')
      this.accountService.get().subscribe(res => {
        let user = res.body;
        if (user && user.role) {
          role = user.role.toLowerCase();
          this.isEmployer = role === 'employer';
        }
      })
    else
      this.isEmployer = role === 'employer';
  }
}
