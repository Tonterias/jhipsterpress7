import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IFrontpageconfig } from 'app/shared/model/frontpageconfig.model';

import { ITEMS_PER_PAGE } from 'app/shared';

import { FrontpageconfigService } from '../entities/frontpageconfig/frontpageconfig.service';

import { LoginModalService, Principal, Account } from 'app/core';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit, OnDestroy {
    account: Account;
    modalRef: NgbModalRef;
    frontpageconfigs: IFrontpageconfig[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;
    currentSearch: string;
    id: number;

    constructor(
        private frontpageconfigService: FrontpageconfigService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
        private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private loginModalService: LoginModalService
    ) {
        this.frontpageconfigs = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.frontpageconfigService
                .query({
                    query: this.currentSearch,
                    page: this.page,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IFrontpageconfig[]>) => this.paginateFrontpageconfigs(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            console.log('FRONTPAGE', this.frontpageconfigs);
//            return;
        }
//        this.frontpageconfigService
//            .query({
//                page: this.page,
//                size: this.itemsPerPage,
//                sort: this.sort()
//            })
//            .subscribe(
//                (res: HttpResponse<IFrontpageconfig[]>) => this.paginateFrontpageconfigs(res.body, res.headers),
//                (res: HttpErrorResponse) => this.onError(res.message)
//            );
//        console.log('FRONTPAGE', this.frontpageconfigs);
    }

    reset() {
        this.page = 0;
        this.frontpageconfigs = [];
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    clear() {
        this.frontpageconfigs = [];
        this.links = {
            last: 0
        };
        this.page = 0;
        this.predicate = 'id';
        this.reverse = true;
        this.currentSearch = '';
        this.loadAll();
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.frontpageconfigs = [];
        this.links = {
            last: 0
        };
        this.page = 0;
        this.predicate = '_score';
        this.reverse = false;
        this.currentSearch = query;
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerAuthenticationSuccess();
        this.registerChangeInFrontpageconfigs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFrontpageconfig) {
        return item.id;
    }

    registerChangeInFrontpageconfigs() {
        this.eventSubscriber = this.eventManager.subscribe('frontpageconfigListModification', response => this.reset());
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateFrontpageconfigs(data: IFrontpageconfig[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.frontpageconfigs.push(data[i]);
        }
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
