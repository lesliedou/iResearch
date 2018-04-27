import { BaseEntity } from './../../shared';

export class Survey implements BaseEntity {
    constructor(
        public id?: number,
        public surveyType?: string,
        public targetTypeReadme?: string,
        public targetType?: string,
        public targetContactInfo?: string,
        public targetContactPerson?: string,
        public targetName?: string,
        public targetId?: string,
        public deptContactInfo?: string,
        public deptContactPerson?: string,
        public surveyDate?: string,
        public departmentId?: number,
    ) {
    }
}
