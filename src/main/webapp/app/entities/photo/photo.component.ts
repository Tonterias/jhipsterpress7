import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ICommunity } from 'app/shared/model/community.model';
import { ICalbum } from 'app/shared/model/calbum.model';
import { IAlbum } from 'app/shared/model/album.model';

import { IPhoto } from 'app/shared/model/photo.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { PhotoService } from './photo.service';

import { CommunityService } from '../community/community.service';
import { CalbumService } from '../calbum/calbum.service';
import { AlbumService } from '../album/album.service';

@Component({
    selector: 'jhi-photo',
    templateUrl: './photo.component.html'
})
export class PhotoComponent implements OnInit, OnDestroy {
    currentAccount: any;
    photos: IPhoto[];
    communities: ICommunity[];
    calbums: ICalbum[];
    albums: IAlbum[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    owner: any;
    isAdmin: boolean;

    constructor(
        private photoService: PhotoService,
        private calbumService: CalbumService,
        private communityService: CommunityService,
        private albumService: AlbumService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.photoService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IPhoto[]>) => this.paginatePhotos(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/photo'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/photo',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.owner = account.id;
            this.principal.hasAnyAuthority(['ROLE_ADMIN']).then( result => {
                this.isAdmin = result;
            });
        });
        this.registerChangeInPhotos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPhoto) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInPhotos() {
        this.eventSubscriber = this.eventManager.subscribe('photoListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private myPhotos() {
        const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
        if ( this.currentAccount.id  != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.communityService
            .query(query)
            .subscribe(
                    (res: HttpResponse<ICommunity[]>) => {
                        this.communities = res.body;
                        this.communitiesCalbums(); // 2
                        },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
    }
// 2
    private communitiesCalbums() {
        const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
        if ( this.communities  != null) {
            const arrayCommmunities = [];
            this.communities.forEach(community => {
                arrayCommmunities.push(community.id);
            });
            query['communityId.in'] = arrayCommmunities;
        }
        this.calbumService
            .query(query)
            .subscribe(
                    (res: HttpResponse<ICalbum[]>) => {
                        this.calbums = res.body;
                        this.photoCommunitiesCalbums(); // 3
                     },
                    (res: HttpErrorResponse) => this.onError(res.message)
            );
//            .subscribe(
//                    (res: HttpResponse<ICalbum[]>) => this.paginateCalbums(res.body, res.headers),
//                    (res: HttpErrorResponse) => this.onError(res.message)
//            );
    }
// 3 de una vez voy a sacar las fotos y equivale al paso 2 mensajes....
    private photoCommunitiesCalbums() {
        const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
        if ( this.calbums  != null) {
            const arrayCalbums = [];
            this.calbums.forEach(calbum => {
                arrayCalbums.push(calbum.id);
            });
            query['calbumId.in'] = arrayCalbums;
        }
        this.photoService
            .query(query)
            .subscribe(
                    (res: HttpResponse<IPhoto[]>) => {
                        this.photos = res.body;
                        this.myUserAlbums(); // 4
                     },
                    (res: HttpErrorResponse) => this.onError(res.message)
            );
//            .subscribe(
//                    (res: HttpResponse<IPhoto[]>) => this.paginatePhotos(res.body, res.headers),
//                    (res: HttpErrorResponse) => this.onError(res.message)
//            );
    }
// 4 Aquí empieza con la otra pata
    private myUserAlbums() {
        const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
        if ( this.currentAccount.id  != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.albumService
            .query(query)
            .subscribe(
                    (res: HttpResponse<IAlbum[]>) => {
                        this.albums = res.body;
                        this.myUserAlbumsPhotos(); // 4b
                     },
                    (res: HttpErrorResponse) => this.onError(res.message)
            );
//            .subscribe(
//                    (res: HttpResponse<IAlbum[]>) => this.paginateAlbums(res.body, res.headers),
//                    (res: HttpErrorResponse) => this.onError(res.message)
//            );
    }
// 4b Y saca las fotos x el lado de los albumes
    private myUserAlbumsPhotos() {
        const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
        if ( this.albums  != null) {
            const arrayAlbums = [];
            this.albums.forEach(album => {
                arrayAlbums.push(album.id);
            });
            query['albumId.in'] = arrayAlbums;
        }
        this.photoService
            .query(query)
//            .subscribe(
//                    (res: HttpResponse<IPhoto[]>) => {
//                        this.photos = res.body;
//                        this.myUserAlbums();//4
//                     },
//                    (res: HttpErrorResponse) => this.onError(res.message)
//            );
            .subscribe(
                    (res: HttpResponse<IPhoto[]>) => {
                        this.photos = this.photos.concat(res.body);
                        this.paginatePhotos(this.photos, res.headers);
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    private paginatePhotos(data: IPhoto[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = data.length;
        this.queryCount = this.totalItems;
        this.photos = data;
        console.log('PHOTOS', this.photos);
        console.log('ALBUMS', this.albums);
        console.log('CALBUMS', this.calbums);
        console.log('communities', this.communities);
    }

//    private paginatePhotos(data: IPhoto[], headers: HttpHeaders) {
//        this.links = this.parseLinks.parse(headers.get('link'));
//        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
//        this.queryCount = this.totalItems;
//        this.photos = data;
//        console.log('PHOTOS', this.photos);
//    }

//    private paginateCalbums(data: ICalbum[], headers: HttpHeaders) {
//        this.links = this.parseLinks.parse(headers.get('link'));
//        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
//        this.queryCount = this.totalItems;
//        this.calbums = data;
//        this.photoCommunitiesCalbums();
//    }
//
//    private paginateCommunities(data: ICommunity[], headers: HttpHeaders) {
//        this.links = this.parseLinks.parse(headers.get('link'));
//        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
//        this.queryCount = this.totalItems;
//        this.communities = data;
//        this.communitiesCalbums();
//    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
