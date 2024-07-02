### 포인터
- 문법
```c
타입* 포인터이름 = &변수이름;

또는

타입* 포인터이름 = 주소값;
```
### 구조체
- 사용자가 C언어의 기본 타입을 가지고 새롭게 정의할 수 있는 사용자 정의 타입
```c
// example
typedef struct BOOK{
    char title[30];
    char author[30];
    int price;
} book;
```
### 구조체 포인터
- 구조체의 주소를 가리키는 포인터
```c
// example
struct book my_book;
struct book* p_my_book;
p_my_book = &my_book;
```
>배열의 경우와는 달리 구조체의 이름은 구조체를 가리키는 주소가 아니다.  
따라서 포인터에 할당할 때는 반드시 주소 연산자(&)를 사용해야 한다.
- 구조체 포인터 접근
	1. (\*구조체포인터).멤버변수이름  
	2. 구조체포인터 -> 멤버변수이름
```c
// example
(*ptr_my_book).author
ptr_my_book -> author  
```