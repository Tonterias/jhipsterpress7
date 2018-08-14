import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterPress07BlogModule } from './blog/blog.module';
import { JhipsterPress07PostModule } from './post/post.module';
import { JhipsterPress07TopicModule } from './topic/topic.module';
import { JhipsterPress07TagModule } from './tag/tag.module';
import { JhipsterPress07CommentModule } from './comment/comment.module';
import { JhipsterPress07MessageModule } from './message/message.module';
import { JhipsterPress07NotificationModule } from './notification/notification.module';
import { JhipsterPress07ProfileModule } from './profile/profile.module';
import { JhipsterPress07CommunityModule } from './community/community.module';
import { JhipsterPress07FollowModule } from './follow/follow.module';
import { JhipsterPress07BlockuserModule } from './blockuser/blockuser.module';
import { JhipsterPress07AlbumModule } from './album/album.module';
import { JhipsterPress07CalbumModule } from './calbum/calbum.module';
import { JhipsterPress07PhotoModule } from './photo/photo.module';
import { JhipsterPress07InterestModule } from './interest/interest.module';
import { JhipsterPress07ActivityModule } from './activity/activity.module';
import { JhipsterPress07CelebModule } from './celeb/celeb.module';
import { JhipsterPress07UrllinkModule } from './urllink/urllink.module';
import { JhipsterPress07FrontpageconfigModule } from './frontpageconfig/frontpageconfig.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterPress07BlogModule,
        JhipsterPress07PostModule,
        JhipsterPress07TopicModule,
        JhipsterPress07TagModule,
        JhipsterPress07CommentModule,
        JhipsterPress07MessageModule,
        JhipsterPress07NotificationModule,
        JhipsterPress07ProfileModule,
        JhipsterPress07CommunityModule,
        JhipsterPress07FollowModule,
        JhipsterPress07BlockuserModule,
        JhipsterPress07AlbumModule,
        JhipsterPress07CalbumModule,
        JhipsterPress07PhotoModule,
        JhipsterPress07InterestModule,
        JhipsterPress07ActivityModule,
        JhipsterPress07CelebModule,
        JhipsterPress07UrllinkModule,
        JhipsterPress07FrontpageconfigModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterPress07EntityModule {}
