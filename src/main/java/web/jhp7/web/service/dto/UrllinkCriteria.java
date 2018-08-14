package web.jhp7.web.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Urllink entity. This class is used in UrllinkResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /urllinks?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UrllinkCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter linkText;

    private StringFilter linkURL;

    private LongFilter postId;

    public UrllinkCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLinkText() {
        return linkText;
    }

    public void setLinkText(StringFilter linkText) {
        this.linkText = linkText;
    }

    public StringFilter getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(StringFilter linkURL) {
        this.linkURL = linkURL;
    }

    public LongFilter getPostId() {
        return postId;
    }

    public void setPostId(LongFilter postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "UrllinkCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (linkText != null ? "linkText=" + linkText + ", " : "") +
                (linkURL != null ? "linkURL=" + linkURL + ", " : "") +
                (postId != null ? "postId=" + postId + ", " : "") +
            "}";
    }

}
