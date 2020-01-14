import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRouteAccessService } from '@app/core/auth/user-route-access-service';
import { JobComponent } from './job.component';


const routes: Routes = [
  {
    path: 'job',
    component: JobComponent,
    canActivate: [UserRouteAccessService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class JobRoutingModule { }
