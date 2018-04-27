import { BaseEntity } from './../../shared';

export class MonthlyReport implements BaseEntity {
    constructor(
        public id?: number,
        public monthName?: string,
        public report?: string,
        public reportTime?: string,
        public reportDate?: string,
        public count?: string,
        public surveyDeptCount?: string,
        public surveyCount?: string,
        public surveyTargetCountJson?: string,
        public surveyTargetCountTotal?: string,
        public surveyTypeCountJson?: string,
        public surveyTypeCountTotal?: string,
        public questionCcountJson?: string,
        public questionCountTotal?: string,
        public questionClosedCountJson?: string,
        public questionClosedCountTota?: string,
        public proposalCountJson?: string,
        public proposalCountTotal?: string,
        public departmentId?: number,
    ) {
    }
}
