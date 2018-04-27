import { BaseEntity } from './../../shared';

export class SurveyQuestionIntf implements BaseEntity {
    constructor(
        public id?: number,
        public taskID?: string,
        public statusType?: string,
        public returnTime?: string,
        public returnNote?: string,
        public createTime?: string,
        public allEndTime?: string,
        public createNote?: string,
        public endTime?: string,
        public lastSolvingTime?: string,
        public solvingNote?: string,
        public endNote?: string,
        public banliResult?: string,
        public executeDeptName?: string,
        public assistantDeptName?: string,
        public feedBackNote?: string,
        public surveyQuestionId?: number,
    ) {
    }
}
