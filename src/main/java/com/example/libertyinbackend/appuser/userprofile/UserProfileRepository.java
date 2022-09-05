package com.example.libertyinbackend.appuser.userprofile;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional(readOnly = true)
public class UserProfileRepository implements JpaRepository<UserProfile, Long> {
    @Override
    public List<UserProfile> findAll() {
        return null;
    }

    @Override
    public List<UserProfile> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserProfile> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserProfile> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserProfile entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserProfile> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserProfile> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserProfile> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserProfile> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserProfile> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserProfile> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserProfile> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserProfile getOne(Long aLong) {
        return null;
    }

    @Override
    public UserProfile getById(Long aLong) {
        return null;
    }

    @Override
    public UserProfile getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserProfile> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserProfile> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserProfile> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserProfile> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserProfile> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserProfile> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserProfile, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
    // handles fetching

}
