import { BaseEntity } from './../../shared';

export class Target implements BaseEntity {
    constructor(
        public id?: number,
        public targetName?: string,
        public targetType?: string,
        public targetTypeReadme?: string,
        public contactPerson?: string,
        public contactPhone?: string,
        public contactEmail?: string,
        public contactAddr?: string,
    ) {
    }
}
