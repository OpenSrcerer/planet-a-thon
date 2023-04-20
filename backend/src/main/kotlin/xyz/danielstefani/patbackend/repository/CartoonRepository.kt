package xyz.danielstefani.patbackend.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.danielstefani.patbackend.model.Cartoon
import java.util.UUID

/**
 * A repository is the "entry point" to the database.
 * When you interact with the repository, you actively modify the database through Java.
 */
interface CartoonRepository : JpaRepository<Cartoon, UUID>