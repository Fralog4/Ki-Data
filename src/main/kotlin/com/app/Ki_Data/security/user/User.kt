package com.app.Ki_Data.security.user

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "_user")
class User(
        @Id @GeneratedValue var id: Int = 0,
        var name: String = "",
        var lastName: String = "",
        var email: String = "",
        @Enumerated(EnumType.STRING) var role: Role = Role.USER,
        @Column private var password: String = ""
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> =
            listOf(SimpleGrantedAuthority(role.name))

    override fun getPassword(): String? = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
