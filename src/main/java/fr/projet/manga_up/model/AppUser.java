package fr.projet.manga_up.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "user", schema = "manga_up", uniqueConstraints = {
        @UniqueConstraint(name = "user_AK", columnNames = {"email"})
})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_user", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fisrtname", length = 50)
    private String fisrtname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "created_at")
    private Instant createdAt;

    @Lob
    @Column(name = "picture", columnDefinition="blob")
    private byte[] img;

    @OneToMany(mappedBy = "user")
    private List<BasketLine> basketLines;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Id_address", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Id_gender", nullable = false)
    private Gender gender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_manga",
            joinColumns = @JoinColumn(name = "Id_user"),
            inverseJoinColumns = @JoinColumn(name = "Id_manga"))
    // @JsonIgnore permet d'ignorer la liste des mangas lors de la searialization des data de l'utilisateur.
    // La liste des mangas ne sera pas ajouté au données de l'utilisateur qui seront renvoyé au front.
    @JsonIgnore
    private Set<Manga> mangas=new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "Id_user"),
        inverseJoinColumns = @JoinColumn(name = "Id_role")
    )
    private Set<AppRole> roles=new HashSet<>();

    public String getFisrtname() {
        return fisrtname;
    }

    public List<BasketLine> getBasketLines() {
        return basketLines;
    }

    public void setBasketLines(List<BasketLine> basketLines) {
        this.basketLines = basketLines;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return fisrtname;
    }

    public void setFisrtname(String fisrtname) {
        this.fisrtname = fisrtname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fisrtname='" + fisrtname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mangas=" + mangas +
                '}';
    }

}
