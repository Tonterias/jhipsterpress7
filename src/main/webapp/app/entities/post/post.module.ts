import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterPress07SharedModule } from 'app/shared';
import {
    PostComponent,
    PostDetailComponent,
    PostUpdateComponent,
    PostDeletePopupComponent,
    PostDeleteDialogComponent,
    postRoute,
    postPopupRoute
} from './';

const ENTITY_STATES = [...postRoute, ...postPopupRoute];

@NgModule({
    imports: [JhipsterPress07SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PostComponent, PostDetailComponent, PostUpdateComponent, PostDeleteDialogComponent, PostDeletePopupComponent],
    entryComponents: [PostComponent, PostUpdateComponent, PostDeleteDialogComponent, PostDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterPress07PostModule {}
