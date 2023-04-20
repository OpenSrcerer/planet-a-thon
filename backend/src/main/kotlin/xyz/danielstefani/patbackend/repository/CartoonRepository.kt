package xyz.danielstefani.patbackend.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.danielstefani.patbackend.model.Cartoon
import java.util.UUID

interface CartoonRepository : JpaRepository<Cartoon, UUID>