package web.jhp7.web.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import web.jhp7.web.domain.User;
import web.jhp7.web.domain.enumeration.NotificationReason;

/**
 * A DTO for the Notification entity.
 */
public class NotificationDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    private Instant notificationDate;

    @NotNull
    private NotificationReason notificationReason;

    @Size(min = 2, max = 100)
    private String notificationText;

    private Boolean isDeliverd;

//    private Long userId;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Instant notificationDate) {
        this.notificationDate = notificationDate;
    }

    public NotificationReason getNotificationReason() {
        return notificationReason;
    }

    public void setNotificationReason(NotificationReason notificationReason) {
        this.notificationReason = notificationReason;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public Boolean isIsDeliverd() {
        return isDeliverd;
    }

    public void setIsDeliverd(Boolean isDeliverd) {
        this.isDeliverd = isDeliverd;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isDeliverd == null) ? 0 : isDeliverd.hashCode());
		result = prime * result + ((notificationDate == null) ? 0 : notificationDate.hashCode());
		result = prime * result + ((notificationReason == null) ? 0 : notificationReason.hashCode());
		result = prime * result + ((notificationText == null) ? 0 : notificationText.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationDTO other = (NotificationDTO) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDeliverd == null) {
			if (other.isDeliverd != null)
				return false;
		} else if (!isDeliverd.equals(other.isDeliverd))
			return false;
		if (notificationDate == null) {
			if (other.notificationDate != null)
				return false;
		} else if (!notificationDate.equals(other.notificationDate))
			return false;
		if (notificationReason != other.notificationReason)
			return false;
		if (notificationText == null) {
			if (other.notificationText != null)
				return false;
		} else if (!notificationText.equals(other.notificationText))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotificationDTO [id=" + id + ", creationDate=" + creationDate + ", notificationDate=" + notificationDate
				+ ", notificationReason=" + notificationReason + ", notificationText=" + notificationText
				+ ", isDeliverd=" + isDeliverd + ", user=" + user + "]";
	}
  
}
