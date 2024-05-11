# E.SUN-Library-project
E.SUN Library 提供了一個完整的圖書管理系統，使用者可以進行書籍借閱和歸還


## 使用流程
(系統存放在spring-boot-test資料夾內)

* **Index**：[http://localhost:8080/](http://localhost:8080/) 進入首頁。

* **Registeration**：第一次使用，請先進行註冊。點擊首頁的 "註冊" 進入註冊畫面 。

* **Login**：電話號碼和密碼登錄。

* **Book_Inventory**：登錄後，會自動跳轉到書籍庫存頁面，您可以查看所有可借的書籍。若書籍為可借閱請按最後欄位 "借閱" 即可借書。

* **Book_borrowed**：您可以隨時查看您的借閱記錄，包括借閱時間和歸還時間。若想要歸還書籍，按下最後欄位 "歸還" 即可還書。

## 使用技術
- Java 19.0.1
- MySQL
- Maven
- Spring Boot Dependencies 3.1.11
