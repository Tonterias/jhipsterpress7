package web.jhp7.web.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import web.jhp7.web.domain.Community;
import web.jhp7.web.domain.Profile;

/**
 * A DTO for the Follow entity.
 */
public class FollowDTO implements Serializable {

    private Long id;

    private Instant creationDate;
    
    private Community cfollowed;
    
    private Community cfollowing;
    
    private Profile followed;
    
    private Profile following;

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

	public Profile getFollowing() {
		return following;
	}

	public void setFollowing(Profile following) {
		this.following = following;
	}

	public Profile getFollowed() {
		return followed;
	}

	public void setFollowed(Profile followed) {
		this.followed = followed;
	}

	public Community getCfollowing() {
		return cfollowing;
	}

	public void setCfollowing(Community cfollowing) {
		this.cfollowing = cfollowing;
	}

	public Community getCfollowed() {
		return cfollowed;
	}

	public void setCfollowed(Community cfollowed) {
		this.cfollowed = cfollowed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfollowed == null) ? 0 : cfollowed.hashCode());
		result = prime * result + ((cfollowing == null) ? 0 : cfollowing.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((followed == null) ? 0 : followed.hashCode());
		result = prime * result + ((following == null) ? 0 : following.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FollowDTO other = (FollowDTO) obj;
		if (cfollowed == null) {
			if (other.cfollowed != null)
				return false;
		} else if (!cfollowed.equals(other.cfollowed))
			return false;
		if (cfollowing == null) {
			if (other.cfollowing != null)
				return false;
		} else if (!cfollowing.equals(other.cfollowing))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (followed == null) {
			if (other.followed != null)
				return false;
		} else if (!followed.equals(other.followed))
			return false;
		if (following == null) {
			if (other.following != null)
				return false;
		} else if (!following.equals(other.following))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FollowDTO [id=" + id + ", creationDate=" + creationDate + ", cfollowed=" + cfollowed + ", cfollowing="
				+ cfollowing + ", followed=" + followed + ", following=" + following + "]";
	}
}
