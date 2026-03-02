package com.app.Ki_Data.security.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User,Int> {

    fun findByEmail(email : String) : User?

    /**
     * Quando chiami findByEmail, Hibernate fa una SELECT * FROM _user e si tira giù tutti i dati dell'utente
     * (nome, password, ruolo, ecc.)
     * solo per dirti true o false. È uno spreco di risorse!
     *
     * Spring Data ha una "parola magica" potentissima per queste situazioni: existsBy....
     * Se usi questa parola chiave, Spring genererà una query SQL ultra-ottimizzata che è istantanea.
     */
    fun existsByEmail(email: String): Boolean


}