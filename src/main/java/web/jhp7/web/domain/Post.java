package web.jhp7.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Post.
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONE)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @Column(name = "publication_date")
    private Instant publicationDate;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "headline", length = 100, nullable = false)
    private String headline;

    @Size(min = 2, max = 1000)
    @Column(name = "leadtext", length = 1000)
    private String leadtext;

    @NotNull
    @Size(min = 2, max = 65000)
    @Column(name = "bodytext", length = 65000, nullable = false)
    private String bodytext;

    @Size(min = 2, max = 1000)
    @Column(name = "quote", length = 1000)
    private String quote;

    @Size(min = 2, max = 2000)
    @Column(name = "conclusion", length = 2000)
    private String conclusion;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink urllink;

    @OneToMany(mappedBy = "post")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("posts")
    private Blog blog;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("posts")
    private Profile profile;

    @ManyToMany(mappedBy = "posts")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(mappedBy = "posts")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Topic> topics = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Post creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public Post publicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getHeadline() {
        return headline;
    }

    public Post headline(String headline) {
        this.headline = headline;
        return this;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLeadtext() {
        return leadtext;
    }

    public Post leadtext(String leadtext) {
        this.leadtext = leadtext;
        return this;
    }

    public void setLeadtext(String leadtext) {
        this.leadtext = leadtext;
    }

    public String getBodytext() {
        return bodytext;
    }

    public Post bodytext(String bodytext) {
        this.bodytext = bodytext;
        return this;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext;
    }

    public String getQuote() {
        return quote;
    }

    public Post quote(String quote) {
        this.quote = quote;
        return this;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getConclusion() {
        return conclusion;
    }

    public Post conclusion(String conclusion) {
        this.conclusion = conclusion;
        return this;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public byte[] getImage() {
        return image;
    }

    public Post image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Post imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Urllink getUrllink() {
        return urllink;
    }

    public Post urllink(Urllink urllink) {
        this.urllink = urllink;
        return this;
    }

    public void setUrllink(Urllink urllink) {
        this.urllink = urllink;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Post comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Post addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
        return this;
    }

    public Post removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setPost(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Blog getBlog() {
        return blog;
    }

    public Post blog(Blog blog) {
        this.blog = blog;
        return this;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Profile getProfile() {
        return profile;
    }

    public Post profile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Post tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Post addTag(Tag tag) {
        this.tags.add(tag);
        tag.getPosts().add(this);
        return this;
    }

    public Post removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getPosts().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public Post topics(Set<Topic> topics) {
        this.topics = topics;
        return this;
    }

    public Post addTopic(Topic topic) {
        this.topics.add(topic);
        topic.getPosts().add(this);
        return this;
    }

    public Post removeTopic(Topic topic) {
        this.topics.remove(topic);
        topic.getPosts().remove(this);
        return this;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        if (post.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", headline='" + getHeadline() + "'" +
            ", leadtext='" + getLeadtext() + "'" +
            ", bodytext='" + getBodytext() + "'" +
            ", quote='" + getQuote() + "'" +
            ", conclusion='" + getConclusion() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
