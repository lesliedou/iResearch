import { BaseEntity } from './../../shared';

export class Notes implements BaseEntity {
    constructor(
        public id?: number,
        public subject?: string,
        public content?: string,
    ) {
    }
}
