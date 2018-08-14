package web.jhp7.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Urllink.
 */
@Entity
@Table(name = "urllink")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Urllink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "link_text", nullable = false)
    private String linkText;

    @NotNull
    @Column(name = "link_url", nullable = false)
    private String linkURL;

    @OneToMany(mappedBy = "urllink")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Post> posts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkText() {
        return linkText;
    }

    public Urllink linkText(String linkText) {
        this.linkText = linkText;
        return this;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public Urllink linkURL(String linkURL) {
        this.linkURL = linkURL;
        return this;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Urllink posts(Set<Post> posts) {
        this.posts = posts;
        return this;
    }

    public Urllink addPost(Post post) {
        this.posts.add(post);
        post.setUrllink(this);
        return this;
    }

    public Urllink removePost(Post post) {
        this.posts.remove(post);
        post.setUrllink(null);
        return this;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
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
        Urllink urllink = (Urllink) o;
        if (urllink.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), urllink.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Urllink{" +
            "id=" + getId() +
            ", linkText='" + getLinkText() + "'" +
            ", linkURL='" + getLinkURL() + "'" +
            "}";
    }
}
