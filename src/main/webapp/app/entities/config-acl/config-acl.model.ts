import { BaseEntity } from './../../shared';

export class ConfigAcl implements BaseEntity {
    constructor(
        public id?: number,
        public modName?: string,
        public funcName?: string,
        public aclList?: any,
    ) {
    }
}
