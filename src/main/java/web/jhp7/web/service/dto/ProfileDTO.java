package web.jhp7.web.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.mapstruct.Mapping;

import web.jhp7.web.domain.User;
import web.jhp7.web.domain.enumeration.Children;
import web.jhp7.web.domain.enumeration.CivilStatus;
import web.jhp7.web.domain.enumeration.EthnicGroup;
import web.jhp7.web.domain.enumeration.Eyes;
import web.jhp7.web.domain.enumeration.FutureChildren;
import web.jhp7.web.domain.enumeration.Gender;
import web.jhp7.web.domain.enumeration.Physical;
import web.jhp7.web.domain.enumeration.Purpose;
import web.jhp7.web.domain.enumeration.Religion;
import web.jhp7.web.domain.enumeration.Smoker;
import web.jhp7.web.domain.enumeration.Studies;

/**
 * A DTO for the Profile entity.
 */
public class ProfileDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant creationDate;

    @Lob
    private byte[] image;
    private String imageContentType;

    @NotNull
    private Gender gender;

    @NotNull
    @Size(max = 20)
    private String phone;

    @Size(max = 7500)
    private String bio;

    private Instant birthdate;

    private CivilStatus civilStatus;

    private Gender lookingFor;

    private Purpose purpose;

    private Physical physical;

    private Religion religion;

    private EthnicGroup ethnicGroup;

    private Studies studies;

    @Min(value = -1)
    @Max(value = 20)
    private Integer sibblings;

    private Eyes eyes;

    private Smoker smoker;

    private Children children;

    private FutureChildren futureChildren;

    private Boolean pet;

    private Long userId; //private User user;

    private String firstName;
    
    private String lastName;
    
    private Set<CustomActivityDTO> activities;
    
    private Set<CustomCelebDTO> celebs;
    
    private Set<CustomInterestDTO> interests;
    
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Instant birthdate) {
        this.birthdate = birthdate;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
    }

    public Gender getLookingFor() {
        return lookingFor;
    }

    public void setLookingFor(Gender lookingFor) {
        this.lookingFor = lookingFor;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Physical getPhysical() {
        return physical;
    }

    public void setPhysical(Physical physical) {
        this.physical = physical;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public EthnicGroup getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(EthnicGroup ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public Studies getStudies() {
        return studies;
    }

    public void setStudies(Studies studies) {
        this.studies = studies;
    }

    public Integer getSibblings() {
        return sibblings;
    }

    public void setSibblings(Integer sibblings) {
        this.sibblings = sibblings;
    }

    public Eyes getEyes() {
        return eyes;
    }

    public void setEyes(Eyes eyes) {
        this.eyes = eyes;
    }

    public Smoker getSmoker() {
        return smoker;
    }

    public void setSmoker(Smoker smoker) {
        this.smoker = smoker;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public FutureChildren getFutureChildren() {
        return futureChildren;
    }

    public void setFutureChildren(FutureChildren futureChildren) {
        this.futureChildren = futureChildren;
    }

    public Boolean isPet() {
        return pet;
    }

    public void setPet(Boolean pet) {
        this.pet = pet;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<CustomActivityDTO> getActivities() {
		return activities;
	}

	public void setActivities(Set<CustomActivityDTO> activities) {
		this.activities = activities;
	}

	public Set<CustomCelebDTO> getCelebs() {
		return celebs;
	}

	public void setCelebs(Set<CustomCelebDTO> celebs) {
		this.celebs = celebs;
	}

	public Set<CustomInterestDTO> getInterests() {
		return interests;
	}

	public void setInterests(Set<CustomInterestDTO> interests) {
		this.interests = interests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activities == null) ? 0 : activities.hashCode());
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((celebs == null) ? 0 : celebs.hashCode());
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((civilStatus == null) ? 0 : civilStatus.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((ethnicGroup == null) ? 0 : ethnicGroup.hashCode());
		result = prime * result + ((eyes == null) ? 0 : eyes.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((futureChildren == null) ? 0 : futureChildren.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((imageContentType == null) ? 0 : imageContentType.hashCode());
		result = prime * result + ((interests == null) ? 0 : interests.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lookingFor == null) ? 0 : lookingFor.hashCode());
		result = prime * result + ((pet == null) ? 0 : pet.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((physical == null) ? 0 : physical.hashCode());
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
		result = prime * result + ((religion == null) ? 0 : religion.hashCode());
		result = prime * result + ((sibblings == null) ? 0 : sibblings.hashCode());
		result = prime * result + ((smoker == null) ? 0 : smoker.hashCode());
		result = prime * result + ((studies == null) ? 0 : studies.hashCode());
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
		ProfileDTO other = (ProfileDTO) obj;
		if (activities == null) {
			if (other.activities != null)
				return false;
		} else if (!activities.equals(other.activities))
			return false;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (celebs == null) {
			if (other.celebs != null)
				return false;
		} else if (!celebs.equals(other.celebs))
			return false;
		if (children != other.children)
			return false;
		if (civilStatus != other.civilStatus)
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (ethnicGroup != other.ethnicGroup)
			return false;
		if (eyes != other.eyes)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (futureChildren != other.futureChildren)
			return false;
		if (gender != other.gender)
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
		if (interests == null) {
			if (other.interests != null)
				return false;
		} else if (!interests.equals(other.interests))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lookingFor != other.lookingFor)
			return false;
		if (pet == null) {
			if (other.pet != null)
				return false;
		} else if (!pet.equals(other.pet))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (physical != other.physical)
			return false;
		if (purpose != other.purpose)
			return false;
		if (religion != other.religion)
			return false;
		if (sibblings == null) {
			if (other.sibblings != null)
				return false;
		} else if (!sibblings.equals(other.sibblings))
			return false;
		if (smoker != other.smoker)
			return false;
		if (studies != other.studies)
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
		return "ProfileDTO [id=" + id + ", creationDate=" + creationDate + ", image=" + Arrays.toString(image)
				+ ", imageContentType=" + imageContentType + ", gender=" + gender + ", phone=" + phone + ", bio=" + bio
				+ ", birthdate=" + birthdate + ", civilStatus=" + civilStatus + ", lookingFor=" + lookingFor
				+ ", purpose=" + purpose + ", physical=" + physical + ", religion=" + religion + ", ethnicGroup="
				+ ethnicGroup + ", studies=" + studies + ", sibblings=" + sibblings + ", eyes=" + eyes + ", smoker="
				+ smoker + ", children=" + children + ", futureChildren=" + futureChildren + ", pet=" + pet
				+ ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", activities="
				+ activities + ", celebs=" + celebs + ", interests=" + interests + "]";
	}
}
