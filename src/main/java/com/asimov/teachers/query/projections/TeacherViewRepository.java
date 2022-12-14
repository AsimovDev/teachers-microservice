package com.asimov.teachers.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherViewRepository extends JpaRepository<TeacherView, String> {
    /*
    @Query(value = "SELECT * FROM teacher_view WHERE teacher_id <> :teacherId AND email = :email", nativeQuery = true)
    Optional<TeacherView> getByTeacherIdAndEmail(String customerId, String email);

     */
}
