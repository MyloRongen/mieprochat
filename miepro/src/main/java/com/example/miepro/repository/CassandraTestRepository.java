package com.example.miepro.repository;

import com.example.miepro.model.Post;
import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.UUID;

public interface CassandraTestRepository extends CassandraRepository<Post, UUID> {

}
