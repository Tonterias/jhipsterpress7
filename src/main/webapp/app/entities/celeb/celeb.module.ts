import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterPress07SharedModule } from 'app/shared';
import {
    CelebComponent,
    CelebDetailComponent,
    CelebUpdateComponent,
    CelebDeletePopupComponent,
    CelebDeleteDialogComponent,
    celebRoute,
    celebPopupRoute
} from './';

const ENTITY_STATES = [...celebRoute, ...celebPopupRoute];

@NgModule({
    imports: [JhipsterPress07SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CelebComponent, CelebDetailComponent, CelebUpdateComponent, CelebDeleteDialogComponent, CelebDeletePopupComponent],
    entryComponents: [CelebComponent, CelebUpdateComponent, CelebDeleteDialogComponent, CelebDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterPress07CelebModule {}
