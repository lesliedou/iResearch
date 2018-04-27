import { BaseEntity } from './../../shared';

export class Onsite implements BaseEntity {
    constructor(
        public id?: number,
        public report?: string,
        public onsiteTime?: string,
        public accompany?: string,
        public onsiteAddr?: string,
        public onsiteInfo?: string,
        public targetId?: string,
        public targetName?: string,
        public surveyType?: string,
        public infoType?: string,
        public adviceDeptId?: string,
        public adviceReadTime?: string,
        public adviceReadUuid?: string,
        public adviceStatus?: string,
        public departmentId?: number,
    ) {
    }
}
