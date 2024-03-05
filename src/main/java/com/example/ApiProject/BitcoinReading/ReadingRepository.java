package com.example.ApiProject.BitcoinReading;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends MongoRepository<Reading, ObjectId> {


}
