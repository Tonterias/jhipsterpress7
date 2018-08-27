package web.jhp7.web.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import web.jhp7.web.domain.Profile;

/**
 * A DTO for the Comment entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 65000)
    private String commentText;

    private Boolean isOffensive;

    private Long postId;

    private Long userId;
    
    private Long commentProfileId;
    
    private String commentProfileUserFirstName;
    
    private String commentProfileUserLastName;
    
    @Lob
    private byte[] profileImage;
    private String profileImageContentType;

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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Boolean isIsOffensive() {
        return isOffensive;
    }

    public void setIsOffensive(Boolean isOffensive) {
        this.isOffensive = isOffensive;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCommentProfileId() {
		return commentProfileId;
	}

	public void setCommentProfileId(Long commentProfileId) {
		this.commentProfileId = commentProfileId;
	}

	public String getCommentProfileUserFirstName() {
		return commentProfileUserFirstName;
	}

	public void setCommentProfileUserFirstName(String commentProfileUserFirstName) {
		this.commentProfileUserFirstName = commentProfileUserFirstName;
	}

	public String getCommentProfileUserLastName() {
		return commentProfileUserLastName;
	}

	public void setCommentProfileUserLastName(String commentProfileUserLastName) {
		this.commentProfileUserLastName = commentProfileUserLastName;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageContentType() {
		return profileImageContentType;
	}

	public void setProfileImageContentType(String profileImageContentType) {
		this.profileImageContentType = profileImageContentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentProfileId == null) ? 0 : commentProfileId.hashCode());
		result = prime * result + ((commentProfileUserFirstName == null) ? 0 : commentProfileUserFirstName.hashCode());
		result = prime * result + ((commentProfileUserLastName == null) ? 0 : commentProfileUserLastName.hashCode());
		result = prime * result + ((commentText == null) ? 0 : commentText.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isOffensive == null) ? 0 : isOffensive.hashCode());
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
		result = prime * result + Arrays.hashCode(profileImage);
		result = prime * result + ((profileImageContentType == null) ? 0 : profileImageContentType.hashCode());
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
		CommentDTO other = (CommentDTO) obj;
		if (commentProfileId == null) {
			if (other.commentProfileId != null)
				return false;
		} else if (!commentProfileId.equals(other.commentProfileId))
			return false;
		if (commentProfileUserFirstName == null) {
			if (other.commentProfileUserFirstName != null)
				return false;
		} else if (!commentProfileUserFirstName.equals(other.commentProfileUserFirstName))
			return false;
		if (commentProfileUserLastName == null) {
			if (other.commentProfileUserLastName != null)
				return false;
		} else if (!commentProfileUserLastName.equals(other.commentProfileUserLastName))
			return false;
		if (commentText == null) {
			if (other.commentText != null)
				return false;
		} else if (!commentText.equals(other.commentText))
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
		if (isOffensive == null) {
			if (other.isOffensive != null)
				return false;
		} else if (!isOffensive.equals(other.isOffensive))
			return false;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		if (!Arrays.equals(profileImage, other.profileImage))
			return false;
		if (profileImageContentType == null) {
			if (other.profileImageContentType != null)
				return false;
		} else if (!profileImageContentType.equals(other.profileImageContentType))
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
		return "CommentDTO [id=" + id + ", creationDate=" + creationDate + ", commentText=" + commentText
				+ ", isOffensive=" + isOffensive + ", postId=" + postId + ", userId=" + userId + ", commentProfileId="
				+ commentProfileId + ", commentProfileUserFirstName=" + commentProfileUserFirstName
				+ ", commentProfileUserLastName=" + commentProfileUserLastName + ", profileImage="
				+ Arrays.toString(profileImage) + ", profileImageContentType=" + profileImageContentType + "]";
	}
}
