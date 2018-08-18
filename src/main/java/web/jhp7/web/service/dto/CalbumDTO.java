package web.jhp7.web.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;

import web.jhp7.web.domain.Community;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Calbum entity.
 */
public class CalbumDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

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
		CalbumDTO other = (CalbumDTO) obj;
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalbumDTO [id=" + id + ", creationDate=" + creationDate + ", title=" + title + ", community="
				+ community + "]";
	}  
}
