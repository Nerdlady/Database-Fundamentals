package student.system.domain;

import student.system.domain.enums.ResourceType;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "resources")
public class Resource {
    private Long id;
    private String name;
    private ResourceType type;
    private String URL;
    private Course course;
    private Set<License> licenses;

    public Resource() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    @Column(name = "url",nullable = false)
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToMany(mappedBy = "resource")
    public Set<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(Set<License> licenses) {
        this.licenses = licenses;
    }
}
