import { KeyValue } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { JobService } from '@app/core/service/job/job-service';
import { JobKeyValue } from '@app/shared/shared-common/key-value/job-key-value';

@Component({
  selector: 'app-job-crud',
  templateUrl: './job-crud.component.html',
  styleUrls: ['./job-crud.component.scss']
})
export class JobCrudComponent implements OnInit {

  categoryTypes: KeyValue<number, string>[] = [];
  cooperationTypes: KeyValue<number, string>[] = [];
  requiredGenders: KeyValue<number, string>[] = [];
  jobForm = new FormGroup({
    categoryType: new FormControl('', [
      Validators.required
    ]),
    cooperationType: new FormControl('', [
      Validators.required
    ]),
    requiredGender: new FormControl('', [
      Validators.required
    ]),
    description: new FormControl('', [
      Validators.required,
      Validators
        .minLength(50)
    ])
  })
  constructor(
    jobKeyValues: JobKeyValue,
    private jobService: JobService
  ) {
    jobKeyValues.getCategoryTypes().subscribe(
      res => this.categoryTypes = res
    )
    jobKeyValues.getCooperationTypes().subscribe(
      res => this.cooperationTypes = res
    )
    jobKeyValues.getRequiredGenders().subscribe(
      res => this.requiredGenders = res
    )
  }

  ngOnInit(): void { }

  submit() {
    this.jobService.add(this.jobForm.value).subscribe(() => { }, error => console.log(error));
  }
}
