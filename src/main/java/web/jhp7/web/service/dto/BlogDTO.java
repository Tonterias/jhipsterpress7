package web.jhp7.web.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;

import web.jhp7.web.domain.Community;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Lob;

/**
 * A DTO for the Blog entity.
 */
public class BlogDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

    @Lob
    private byte[] image;
    private String imageContentType;

    private Community community;
//    private Long communityId;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((community == null) ? 0 : community.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((imageContentType == null) ? 0 : imageContentType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		BlogDTO other = (BlogDTO) obj;
		if (community == null) {
			if (other.community != null)
				return false;
		} else if (!community.equals(other.community))
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
		if (!Arrays.equals(image, other.image))
			return false;
		if (imageContentType == null) {
			if (other.imageContentType != null)
				return false;
		} else if (!imageContentType.equals(other.imageContentType))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BlogDTO [id=" + id + ", creationDate=" + creationDate + ", title=" + title + ", image="
				+ Arrays.toString(image) + ", imageContentType=" + imageContentType + ", community=" + community + "]";
	}
//    public Long getCommunityId() {
//        return communityId;
//    }
//
//    public void setCommunityId(Long communityId) {
//        this.communityId = communityId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        BlogDTO blogDTO = (BlogDTO) o;
//        if (blogDTO.getId() == null || getId() == null) {
//            return false;
//        }
//        return Objects.equals(getId(), blogDTO.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(getId());
//    }
//
//    @Override
//    public String toString() {
//        return "BlogDTO{" +
//            "id=" + getId() +
//            ", creationDate='" + getCreationDate() + "'" +
//            ", title='" + getTitle() + "'" +
//            ", image='" + getImage() + "'" +
//            ", community=" + getCommunityId() +
//            "}";
//    }
}
