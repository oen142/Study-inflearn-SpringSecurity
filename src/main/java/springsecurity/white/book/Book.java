package springsecurity.white.book;

import springsecurity.white.account.Account;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;
}

