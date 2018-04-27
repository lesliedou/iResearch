import { BaseEntity } from './../../shared';

export class UploadInfo implements BaseEntity {
    constructor(
        public id?: number,
        public infoType?: string,
        public subj?: string,
        public content?: string,
        public departmentId?: number,
    ) {
    }
}
