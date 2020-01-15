import { KeyValue } from '@angular/common';
import { Observable, of } from 'rxjs';

export class JobKeyValue {
  getCategoryTypes(): Observable<KeyValue<number, string>[]> {
    const array: KeyValue<number, string>[] = [
      {
        key: 0,
        value: 'وب و برنامه نویسی'
      },
      {
        key: 1,
        value: 'مدیریت شبکه'
      },
      {
        key: 2,
        value: 'اپراتور'
      }
    ];
    return of(array);
  }
  getCooperationTypes(): Observable<KeyValue<number, string>[]> {
    const array: KeyValue<number, string>[] = [
      {
        key: 0,
        value: 'پاره وقت'
      },
      {
        key: 1,
        value: 'تمام وقت'
      },
      {
        key: 2,
        value: 'دورکاری'
      },
      {
        key: 3,
        value: 'کارآموزی'
      }
    ];
    return of(array);
  }
  getRequiredGenders(): Observable<KeyValue<number, string>[]> {
    const array: KeyValue<number, string>[] = [
      {
        key: 0,
        value: 'خانم'
      },
      {
        key: 1,
        value: 'آقا'
      }
    ];
    return of(array);
  }
}
