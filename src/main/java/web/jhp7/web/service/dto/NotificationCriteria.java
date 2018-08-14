package web.jhp7.web.service.dto;

import java.io.Serializable;
import web.jhp7.web.domain.enumeration.NotificationReason;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the Notification entity. This class is used in NotificationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /notifications?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NotificationCriteria implements Serializable {
    /**
     * Class for filtering NotificationReason
     */
    public static class NotificationReasonFilter extends Filter<NotificationReason> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private InstantFilter creationDate;

    private InstantFilter notificationDate;

    private NotificationReasonFilter notificationReason;

    private StringFilter notificationText;

    private BooleanFilter isDeliverd;

    private LongFilter userId;

    public NotificationCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(InstantFilter creationDate) {
        this.creationDate = creationDate;
    }

    public InstantFilter getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(InstantFilter notificationDate) {
        this.notificationDate = notificationDate;
    }

    public NotificationReasonFilter getNotificationReason() {
        return notificationReason;
    }

    public void setNotificationReason(NotificationReasonFilter notificationReason) {
        this.notificationReason = notificationReason;
    }

    public StringFilter getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(StringFilter notificationText) {
        this.notificationText = notificationText;
    }

    public BooleanFilter getIsDeliverd() {
        return isDeliverd;
    }

    public void setIsDeliverd(BooleanFilter isDeliverd) {
        this.isDeliverd = isDeliverd;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NotificationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (notificationDate != null ? "notificationDate=" + notificationDate + ", " : "") +
                (notificationReason != null ? "notificationReason=" + notificationReason + ", " : "") +
                (notificationText != null ? "notificationText=" + notificationText + ", " : "") +
                (isDeliverd != null ? "isDeliverd=" + isDeliverd + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
            "}";
    }

}
