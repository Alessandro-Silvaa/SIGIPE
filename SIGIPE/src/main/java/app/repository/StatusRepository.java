package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

}
