import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { IResearchConfigAclModule } from './config-acl/config-acl.module';
import { IResearchAclModule } from './acl/acl.module';
import { IResearchDepartmentModule } from './department/department.module';
import { IResearchUploadInfoModule } from './upload-info/upload-info.module';
import { IResearchMonthlyReportModule } from './monthly-report/monthly-report.module';
import { IResearchNotesModule } from './notes/notes.module';
import { IResearchOnsiteModule } from './onsite/onsite.module';
import { IResearchOnsiteProcessModule } from './onsite-process/onsite-process.module';
import { IResearchRoleModule } from './role/role.module';
import { IResearchSurveyModule } from './survey/survey.module';
import { IResearchSurveyQuestionModule } from './survey-question/survey-question.module';
import { IResearchSurveyQuestionFlowModule } from './survey-question-flow/survey-question-flow.module';
import { IResearchSurveyQuestionIntfModule } from './survey-question-intf/survey-question-intf.module';
import { IResearchTargetModule } from './target/target.module';
import { IResearchFilesModule } from './files/files.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        IResearchConfigAclModule,
        IResearchAclModule,
        IResearchDepartmentModule,
        IResearchUploadInfoModule,
        IResearchMonthlyReportModule,
        IResearchNotesModule,
        IResearchOnsiteModule,
        IResearchOnsiteProcessModule,
        IResearchRoleModule,
        IResearchSurveyModule,
        IResearchSurveyQuestionModule,
        IResearchSurveyQuestionFlowModule,
        IResearchSurveyQuestionIntfModule,
        IResearchTargetModule,
        IResearchFilesModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchEntityModule {}
