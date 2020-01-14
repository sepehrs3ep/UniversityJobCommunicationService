import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { JobComponent } from './job.component';
import { JobRoutingModule } from './job.routing.module';

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
