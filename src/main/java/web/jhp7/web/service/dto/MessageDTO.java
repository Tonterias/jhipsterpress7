package web.jhp7.web.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;

import web.jhp7.web.domain.Community;
import web.jhp7.web.domain.Profile;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Message entity.
 */
public class MessageDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 8000)
    private String messageText;

    private Boolean isDeliverd;

    private Long communityId;

    private Long profileId;
    
    private Long userId;
    
//    private Community community;
//    
//    private Profile profile;

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

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Boolean isIsDeliverd() {
        return isDeliverd;
    }

    public void setIsDeliverd(Boolean isDeliverd) {
        this.isDeliverd = isDeliverd;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((communityId == null) ? 0 : communityId.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isDeliverd == null) ? 0 : isDeliverd.hashCode());
		result = prime * result + ((messageText == null) ? 0 : messageText.hashCode());
		result = prime * result + ((profileId == null) ? 0 : profileId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		MessageDTO other = (MessageDTO) obj;
		if (communityId == null) {
			if (other.communityId != null)
				return false;
		} else if (!communityId.equals(other.communityId))
			return false;
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
		if (messageText == null) {
			if (other.messageText != null)
				return false;
		} else if (!messageText.equals(other.messageText))
			return false;
		if (profileId == null) {
			if (other.profileId != null)
				return false;
		} else if (!profileId.equals(other.profileId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessageDTO [id=" + id + ", creationDate=" + creationDate + ", messageText=" + messageText
				+ ", isDeliverd=" + isDeliverd + ", communityId=" + communityId + ", profileId=" + profileId
				+ ", userId=" + userId + "]";
	}
}
