package coop.tecso.examen.repository;

import com.google.common.collect.Lists;
import coop.tecso.examen.model.Movement.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    //@Query("SELECT COUNT(m)>0 FROM Movement m WHERE m.currentAccount.id=?1")
    //boolean existMovementsForThisAccount(@Param("accountId") Long accountId);

    @Query("FROM Movement m where m.currentAccount.id = :accountId ORDER BY m.operationDateTime DESC")
    Collection<Movement> findAllByAccountId(@Param("accountId") Long accountId);
    //Page<Movement> findJAJAByAccountId(@Param("accountId") Long accountId,@Param("aPage") Pageable aPage);
}
