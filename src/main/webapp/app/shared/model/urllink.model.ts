import { IPost } from 'app/shared/model//post.model';

export interface IUrllink {
    id?: number;
    linkText?: string;
    linkURL?: string;
    posts?: IPost[];
}

export class Urllink implements IUrllink {
    constructor(public id?: number, public linkText?: string, public linkURL?: string, public posts?: IPost[]) {}
}
