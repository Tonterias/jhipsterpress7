package web.jhp7.web.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;

import web.jhp7.web.domain.User;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Community entity.
 */
public class CommunityDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 100)
    private String communityname;

    @NotNull
    @Size(min = 2, max = 7500)
    private String communitydescription;

    @Lob
    private byte[] image;
    private String imageContentType;

    private Boolean isActive;

//    private User user;
    private Long userId;

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

    public String getCommunityname() {
        return communityname;
    }

    public void setCommunityname(String communityname) {
        this.communityname = communityname;
    }

    public String getCommunitydescription() {
        return communitydescription;
    }

    public void setCommunitydescription(String communitydescription) {
        this.communitydescription = communitydescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((communitydescription == null) ? 0 : communitydescription.hashCode());
//		result = prime * result + ((communityname == null) ? 0 : communityname.hashCode());
//		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + Arrays.hashCode(image);
//		result = prime * result + ((imageContentType == null) ? 0 : imageContentType.hashCode());
//		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
//		result = prime * result + ((user == null) ? 0 : user.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CommunityDTO other = (CommunityDTO) obj;
//		if (communitydescription == null) {
//			if (other.communitydescription != null)
//				return false;
//		} else if (!communitydescription.equals(other.communitydescription))
//			return false;
//		if (communityname == null) {
//			if (other.communityname != null)
//				return false;
//		} else if (!communityname.equals(other.communityname))
//			return false;
//		if (creationDate == null) {
//			if (other.creationDate != null)
//				return false;
//		} else if (!creationDate.equals(other.creationDate))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (!Arrays.equals(image, other.image))
//			return false;
//		if (imageContentType == null) {
//			if (other.imageContentType != null)
//				return false;
//		} else if (!imageContentType.equals(other.imageContentType))
//			return false;
//		if (isActive == null) {
//			if (other.isActive != null)
//				return false;
//		} else if (!isActive.equals(other.isActive))
//			return false;
//		if (user == null) {
//			if (other.user != null)
//				return false;
//		} else if (!user.equals(other.user))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "CommunityDTO [id=" + id + ", creationDate=" + creationDate + ", communityname=" + communityname
//				+ ", communitydescription=" + communitydescription + ", image=" + Arrays.toString(image)
//				+ ", imageContentType=" + imageContentType + ", isActive=" + isActive + ", user=" + user + "]";
//	}
    

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommunityDTO communityDTO = (CommunityDTO) o;
        if (communityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), communityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommunityDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", communityname='" + getCommunityname() + "'" +
            ", communitydescription='" + getCommunitydescription() + "'" +
            ", image='" + getImage() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
