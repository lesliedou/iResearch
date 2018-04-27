import { BaseEntity } from './../../shared';

export class Role implements BaseEntity {
    constructor(
        public id?: number,
        public roleName?: string,
        public remarks?: string,
    ) {
    }
}
