package com.group.libraryapp.dto.book.request;

public class BookCreateRequest {

    private String name;

//    public BookSaveRequest(String name) { 역직렬화할 때는 기본 생성자가 필요한데 이렇게 생성자를 만들면 기본생성자를 추가해야함.
//        this.name = name;                 뿐만 아니라 애초에 필요가 없는 생성자임.
//    }                                     항상 뭐든지 필요에 의해 만들어야 한다.

    public String getName() {
        return name;
    }
}
