package com.group.libraryapp.domain.user.loan_history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistroyRepository extends JpaRepository<UserLoanHistory, Long> {

    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);

    /** UPDATE user_loan_history SET is_return = 1 WHERE user_id = ?**/
    Optional<UserLoanHistory> findByUserIdAndBookName(Long userId, String bookName);

}
