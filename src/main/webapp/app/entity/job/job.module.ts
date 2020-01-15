import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedLibModule } from '@app/shared/shared-lib/shared-lib.module';
import { SharedModule } from '@app/shared/shared.module';
import { JobCrudComponent } from './job-crud/job-crud.component';
import { JobRoutingModule } from './job-routing.module';
import { JobComponent } from './job.component';

@NgModule({
  declarations: [
    JobComponent,
    JobCrudComponent
  ],
  imports: [
    CommonModule,
    JobRoutingModule,
    SharedLibModule,
    SharedModule,
  ],
  exports: [],
  providers: [],
})
export class JobModule { }
