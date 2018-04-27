import { BaseEntity } from './../../shared';

export class Acl implements BaseEntity {
    constructor(
        public id?: number,
        public modName?: string,
        public funcName?: string,
        public aclList?: any,
    ) {
    }
}
