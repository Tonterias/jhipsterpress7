import { Moment } from 'moment';
import { IComment } from 'app/shared/model//comment.model';
import { IPost } from 'app/shared/model//post.model';
import { IMessage } from 'app/shared/model//message.model';
import { IFollow } from 'app/shared/model//follow.model';
import { IBlockuser } from 'app/shared/model//blockuser.model';
import { IInterest } from 'app/shared/model//interest.model';
import { IActivity } from 'app/shared/model//activity.model';
import { ICeleb } from 'app/shared/model//celeb.model';

export const enum Gender {
    MALE = 'MALE',
    FEMALE = 'FEMALE',
    OTHER = 'OTHER'
}

export const enum CivilStatus {
    NA = 'NA',
    SINGLE = 'SINGLE',
    MARRIED = 'MARRIED',
    DIVORCED = 'DIVORCED',
    WIDOWER = 'WIDOWER',
    SEPARATED = 'SEPARATED',
    PAIRED_UP = 'PAIRED_UP',
    OTHER = 'OTHER'
}

export const enum Purpose {
    NOT_INTERESTED = 'NOT_INTERESTED',
    FRIENDSHIP = 'FRIENDSHIP',
    MEET_PEOPLE = 'MEET_PEOPLE',
    STABLE_RELATIONSHIP = 'STABLE_RELATIONSHIP',
    MARRIAGE = 'MARRIAGE',
    OTHER = 'OTHER'
}

export const enum Physical {
    NA = 'NA',
    THIN = 'THIN',
    ATHLETIC = 'ATHLETIC',
    NORMAL = 'NORMAL',
    CORPULENT = 'CORPULENT',
    BULKY = 'BULKY',
    OTHER = 'OTHER'
}

export const enum Religion {
    NA = 'NA',
    ATHEIST = 'ATHEIST',
    AGNOSTIC = 'AGNOSTIC',
    CATHOLIC = 'CATHOLIC',
    JEWISH = 'JEWISH',
    ISLAMIC = 'ISLAMIC',
    OTHER = 'OTHER'
}

export const enum EthnicGroup {
    NA = 'NA',
    MIXED = 'MIXED',
    WHITE = 'WHITE',
    LATIN = 'LATIN',
    GIPSY = 'GIPSY',
    AFRO = 'AFRO',
    HINDU = 'HINDU',
    ARAB = 'ARAB',
    ASIAN = 'ASIAN',
    INDIAN = 'INDIAN',
    OTHER = 'OTHER'
}

export const enum Studies {
    NA = 'NA',
    PRIMARY = 'PRIMARY',
    HIGH_SCHOOL = 'HIGH_SCHOOL',
    TECHNICAL = 'TECHNICAL',
    COLLEGE = 'COLLEGE',
    MASTER = 'MASTER',
    DOCTORATE = 'DOCTORATE',
    OTHER = 'OTHER'
}

export const enum Eyes {
    NA = 'NA',
    BLUE = 'BLUE',
    GREEN = 'GREEN',
    BROWN = 'BROWN',
    BLACK = 'BLACK',
    CHESTNUT = 'CHESTNUT',
    OTHER = 'OTHER'
}

export const enum Smoker {
    NA = 'NA',
    YES = 'YES',
    NO = 'NO',
    OCCASIONALLY = 'OCCASIONALLY'
}

export const enum Children {
    NA = 'NA',
    YES = 'YES',
    NO = 'NO',
    GREATER_THAN_18 = 'GREATER_THAN_18'
}

export const enum FutureChildren {
    NA = 'NA',
    YES = 'YES',
    NO = 'NO'
}

export interface IProfile {
    id?: number;
    creationDate?: Moment;
    imageContentType?: string;
    image?: any;
    gender?: Gender;
    phone?: string;
    bio?: string;
    birthdate?: Moment;
    civilStatus?: CivilStatus;
    lookingFor?: Gender;
    purpose?: Purpose;
    physical?: Physical;
    religion?: Religion;
    ethnicGroup?: EthnicGroup;
    studies?: Studies;
    sibblings?: number;
    eyes?: Eyes;
    smoker?: Smoker;
    children?: Children;
    futureChildren?: FutureChildren;
    pet?: boolean;
    userId?: number;
    comments?: IComment[];
    posts?: IPost[];
    messages?: IMessage[];
    followeds?: IFollow[];
    followings?: IFollow[];
    blockedusers?: IBlockuser[];
    blockingusers?: IBlockuser[];
    interests?: IInterest[];
    activities?: IActivity[];
    celebs?: ICeleb[];
}

export class Profile implements IProfile {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public imageContentType?: string,
        public image?: any,
        public gender?: Gender,
        public phone?: string,
        public bio?: string,
        public birthdate?: Moment,
        public civilStatus?: CivilStatus,
        public lookingFor?: Gender,
        public purpose?: Purpose,
        public physical?: Physical,
        public religion?: Religion,
        public ethnicGroup?: EthnicGroup,
        public studies?: Studies,
        public sibblings?: number,
        public eyes?: Eyes,
        public smoker?: Smoker,
        public children?: Children,
        public futureChildren?: FutureChildren,
        public pet?: boolean,
        public userId?: number,
        public comments?: IComment[],
        public posts?: IPost[],
        public messages?: IMessage[],
        public followeds?: IFollow[],
        public followings?: IFollow[],
        public blockedusers?: IBlockuser[],
        public blockingusers?: IBlockuser[],
        public interests?: IInterest[],
        public activities?: IActivity[],
        public celebs?: ICeleb[]
    ) {
        this.pet = false;
    }
}
