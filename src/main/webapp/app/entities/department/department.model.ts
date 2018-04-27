import { BaseEntity } from './../../shared';

export class Department implements BaseEntity {
    constructor(
        public id?: number,
        public deptName?: string,
        public deptCode?: string,
        public deptType?: string,
        public contactPerson?: string,
        public contactInfo?: string,
    ) {
    }
}
