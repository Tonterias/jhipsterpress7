import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterPress07SharedModule } from 'app/shared';
import {
    BlockuserComponent,
    BlockuserDetailComponent,
    BlockuserUpdateComponent,
    BlockuserDeletePopupComponent,
    BlockuserDeleteDialogComponent,
    blockuserRoute,
    blockuserPopupRoute
} from './';

const ENTITY_STATES = [...blockuserRoute, ...blockuserPopupRoute];

@NgModule({
    imports: [JhipsterPress07SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BlockuserComponent,
        BlockuserDetailComponent,
        BlockuserUpdateComponent,
        BlockuserDeleteDialogComponent,
        BlockuserDeletePopupComponent
    ],
    entryComponents: [BlockuserComponent, BlockuserUpdateComponent, BlockuserDeleteDialogComponent, BlockuserDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterPress07BlockuserModule {}
