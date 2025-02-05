package com.example.testtask.repo;

import com.example.testtask.model.Readers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReaderRepo extends JpaRepository<Readers, Long> {
    @Query(value = "select r.* from readers r join " +
            "(select r.*, count(lj.type) as count_takings from readers r " +
            "left join (select t.reader_id, t.book_id, t.type from transactions t " +
            "where t.type = 'TAKING') lj " +
            "on r.id = lj.reader_id " +
            "group by r.id) s " +
            "on r.id = s.id " +
            "order by count_takings desc " +
            "limit 1",
            nativeQuery = true)
    Readers findMostReadingReader();

    @Query(value = """
            select r.* from readers r
            join (select s1.reader_id, s1.count_takings - coalesce(s2.count_returnings, 0) unreturned
                from (select t.reader_id, count(reader_id) count_takings
                    from transactions t
                    where t.type = 'TAKING'
                    group by t.reader_id) s1
                left join (select t.reader_id, count(reader_id) count_returnings
                    from transactions t
                    where t.type = 'RETURNING'
                    group by t.reader_id) s2
                on s1.reader_id = s2.reader_id) s
            on r.id = s.reader_id
            where s.unreturned > 0
            order by s.unreturned desc""",
            nativeQuery = true)
    List<Readers> findAllDebtors();
}
