import { BaseEntity } from './../../shared';

export class SurveyQuestionFlow implements BaseEntity {
    constructor(
        public id?: number,
        public confirm?: string,
        public remarks?: string,
        public surveyQuestionId?: number,
    ) {
    }
}
