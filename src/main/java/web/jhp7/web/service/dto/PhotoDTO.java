package web.jhp7.web.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;

import web.jhp7.web.domain.Album;
import web.jhp7.web.domain.Calbum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Photo entity.
 */
public class PhotoDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @Lob
    private byte[] image;
    private String imageContentType;

//    private Long albumId;
//
//    private Long calbumId;

    private Album album;

    private Calbum calbum;
    
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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Calbum getCalbum() {
		return calbum;
	}

	public void setCalbum(Calbum calbum) {
		this.calbum = calbum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((calbum == null) ? 0 : calbum.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((imageContentType == null) ? 0 : imageContentType.hashCode());
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
		PhotoDTO other = (PhotoDTO) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (calbum == null) {
			if (other.calbum != null)
				return false;
		} else if (!calbum.equals(other.calbum))
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
		return true;
	}

	@Override
	public String toString() {
		return "PhotoDTO [id=" + id + ", creationDate=" + creationDate + ", image=" + Arrays.toString(image)
				+ ", imageContentType=" + imageContentType + ", album=" + album + ", calbum=" + calbum + "]";
	}
}
