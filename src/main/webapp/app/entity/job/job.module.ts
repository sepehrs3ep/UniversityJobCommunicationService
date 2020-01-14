import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { JobRoutingModule } from './job-routing.module';
import { JobComponent } from './job.component';

@NgModule({
  declarations: [
    JobComponent
  ],
  imports: [
    CommonModule,
    JobRoutingModule
  ],
  exports: [],
  providers: [],
})
export class JobModule { }
