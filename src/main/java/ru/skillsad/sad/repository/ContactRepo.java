package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.skillsad.sad.domain.contact.Contact;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
