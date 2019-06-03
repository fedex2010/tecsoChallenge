package coop.tecso.examen.repository;

import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

}
