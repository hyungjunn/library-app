package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserReposritory;
import com.group.libraryapp.domain.user.loan_history.UserLoanHistory;
import com.group.libraryapp.domain.user.loan_history.UserLoanHistroyRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository; // 저장소니까 bookRepository라고 명시해주기!
    private final UserLoanHistroyRepository userLoanHistroyRepository;
    private final UserReposritory userReposritory;

    public BookService(BookRepository bookRepository, UserLoanHistroyRepository userLoanHistroyRepository, UserReposritory userReposritory) {
        this.bookRepository = bookRepository;
        this.userLoanHistroyRepository = userLoanHistroyRepository;
        this.userReposritory = userReposritory;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 책 정보를 가져온다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 만약 리퀘스트 > 북네임 > is_return 이 '1'이라면 throw
        if (userLoanHistroyRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException(String.format("이 book(%s)는 대출이 된 책입니다.", request.getBookName()));
        }

        //유저 정보를 가져온다.
        User user = userReposritory.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        // 리퀘스트 > 유저네임 > id & 리퀘스트 > 북네임 >>> is_return = '1'
        //userLoanHistroyRepository.save(new UserLoanHistory(user, book.getName()));
        user.loanBook(book.getName());
    }

//    @Transactional
//    public void returnBook(BookReturnRequest request) {
//        //유저 정보를 가져온다
//        User user = userReposritory.findByName(request.getUserName())
//                .orElseThrow(IllegalArgumentException::new);
//        //대출정보에서 is_return을 false에서 true로 바꿔준다
//        // - 대출정보를 가져온다
//        UserLoanHistory history = userLoanHistroyRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
//                .orElseThrow(IllegalArgumentException::new);
//        // - 이 대출정보의 is_return을 true로 바꿔준다
//        history.doReturn();
//        // 영속성 컨텍스트로 인해 save를 따로 안해줘도 변경감지를 해 바꿔준다
//    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        //유저 정보를 가져온다
        User user = userReposritory.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        //대출정보에서 is_return을 false에서 true로 바꿔준다
        // - 대출정보를 가져온다
        UserLoanHistory history = userLoanHistroyRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        user.returnBook(history.getBookName());
    }

}
