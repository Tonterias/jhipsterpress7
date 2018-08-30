import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IBlog } from 'app/shared/model/blog.model';
import { BlogService } from './blog.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';

import { Principal } from 'app/core';

@Component({
    selector: 'jhi-blog-update',
    templateUrl: './blog-update.component.html'
})
export class BlogUpdateComponent implements OnInit {
    private _blog: IBlog;
    isSaving: boolean;

    communities: ICommunity[];
    creationDate: string;
    currentAccount: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private blogService: BlogService,
        private communityService: CommunityService,
        private elementRef: ElementRef,
        private principal: Principal,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ blog }) => {
            this.blog = blog;
        });
        console.log('1.-Testing: Printing this.isSaving = false', this.isSaving);
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.myBlogs(this.currentAccount);
        });
//        this.communityService.query().subscribe(
//            (res: HttpResponse<ICommunity[]>) => {
//                this.communities = res.body;
//            },
//            (res: HttpErrorResponse) => this.onError(res.message)
//        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.blog, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.blog.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        if (this.blog.id !== undefined) {
            this.subscribeToSaveResponse(this.blogService.update(this.blog));
        } else {
            this.subscribeToSaveResponse(this.blogService.create(this.blog));
        }
    }

    private myBlogs(currentAccount) {
        const query = {
//                page: this.page - 1,
//                size: this.itemsPerPage,
//                sort: this.sort()
            };
        if ( this.currentAccount.id  != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.communityService
            .query(query)
            .subscribe(
                    (res: HttpResponse<ICommunity[]>) => {
                        this.communities = res.body;
                        console.log('4.- Printing the res.body: ', res.body);
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
            );
        console.log('5.- Printing the this.currentAccount.id', this.currentAccount.id);
    }
//            .subscribe(
//                (res: HttpResponse<ICommunity[]>) => this.paginateCommunities(res.body, res.headers),
//                (res: HttpErrorResponse) => this.onError(res.message)
//            );
//    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBlog>>) {
        result.subscribe((res: HttpResponse<IBlog>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCommunityById(index: number, item: ICommunity) {
        return item.id;
    }
    get blog() {
        return this._blog;
    }

    set blog(blog: IBlog) {
        this._blog = blog;
        this.creationDate = moment(blog.creationDate).format(DATE_TIME_FORMAT);
    }
}
