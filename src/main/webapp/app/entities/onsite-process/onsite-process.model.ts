import { BaseEntity } from './../../shared';

export class OnsiteProcess implements BaseEntity {
    constructor(
        public id?: number,
        public confirm?: string,
        public remarks?: string,
        public onsiteId?: number,
    ) {
    }
}
