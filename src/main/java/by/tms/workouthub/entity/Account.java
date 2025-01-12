package by.tms.workouthub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;


}