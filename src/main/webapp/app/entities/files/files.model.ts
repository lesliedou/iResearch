import { BaseEntity } from './../../shared';

export class Files implements BaseEntity {
    constructor(
        public id?: number,
        public funcName?: string,
        public linkId?: string,
        public subId?: string,
        public subId2?: string,
        public authStr?: string,
        public subType?: string,
        public fname?: string,
        public rawName?: string,
        public fsize?: string,
        public ftype?: string,
        public remarks?: string,
    ) {
    }
}
